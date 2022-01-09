package gui;

import Game.Player;
import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import replay.PlayerTurn;

import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class endSceneController implements Initializable {

    private static final String HARD_AI = "Trudny";
    private static final String MEDIUM_AI = "Średni";
    private static final String EASY_AI = "Łatwy";
    private static final String PATH_TO_MENU = "fxml/Menu.fxml";
    private static final String PATH_TO_REPLAY = "fxml/Replay.fxml";
    @FXML
    private Button mainMenuButton;

    @FXML
    private Button replayButton;

    private List<Player> playerList;

    private List<PlayerTurn> listOfTurns;

    private DatabaseConnection databaseConnection;

    @FXML
    private Label scoresLabel;


    public void setComponents(List<Player> playerList, List<PlayerTurn> listOfTurns) {
        playerList.sort(Comparator.comparing(Player::getPoints));
        this.playerList = playerList;
        this.listOfTurns = listOfTurns;
        updateStatistics();
        updateScoresLabel();
    }

    private void updateStatistics() {
        initializeDatabase();
        Player firstPlayer = playerList.get(0);
        List<Player> listOfHumanPlayers = selectHumanPlayers();
        if (firstPlayer.isItHuman()) {
            if (listOfHumanPlayers.size() > 1) {
                updateStatisticsHelper(firstPlayer, true, DatabaseConnection.PVP);
                for (int i = 1; i < listOfHumanPlayers.size(); i++) {
                    updateStatisticsHelper(listOfHumanPlayers.get(i), false, DatabaseConnection.PVP);
                }
            } else {
                List<Player> listOfAIPlayers = selectAIPlayers();
                if (listOfAIPlayers.stream().anyMatch(player -> player.getAiPlayerLevel().equals(HARD_AI))) {
                    updateStatisticsHelper(firstPlayer, true, DatabaseConnection.HARD);
                } else if (listOfAIPlayers.stream().anyMatch(player -> player.getAiPlayerLevel().equals(MEDIUM_AI))) {
                    updateStatisticsHelper(firstPlayer, true, DatabaseConnection.MEDIUM);
                } else {
                    updateStatisticsHelper(firstPlayer, true, DatabaseConnection.EASY);
                }
            }
        } else {
            switch (firstPlayer.getAiPlayerLevel()) {
                case HARD_AI -> listOfHumanPlayers.forEach(player -> updateStatisticsHelper(player, false, DatabaseConnection.HARD));
                case MEDIUM_AI -> listOfHumanPlayers.forEach(player -> updateStatisticsHelper(player, false, DatabaseConnection.MEDIUM));
                case EASY_AI -> listOfHumanPlayers.forEach(player -> updateStatisticsHelper(player, false, DatabaseConnection.EASY));
            }
        }
    }

    private List<Player> selectHumanPlayers() {
        return playerList.stream().filter(Player::isItHuman).collect(Collectors.toList());
    }

    private List<Player> selectAIPlayers() {
        return playerList.stream().filter(player -> !player.isItHuman()).collect(Collectors.toList());
    }

    private void updateScoresLabel() {
        String scoresLabelText;
        Player winner = playerList.stream().max(Comparator.comparing(Player::getPoints)).stream().findFirst().get();
        scoresLabelText = "Wygrał gracz: " + winner.getPlayerName() + "\n"
                + "zdobywając: " + winner.getPoints() + " pkt";
        scoresLabel.setText(scoresLabelText);
    }

    private void updateStatisticsHelper(Player player, boolean isGameWon, String gameMode) {
        try {
            databaseConnection.updateUserStatistics(isGameWon, gameMode, player.getPoints(), player.getMaxPointsPlayerCouldGet(), player.getPlayerName());
        } catch (SQLException e) {
            new AlertHandler().display(Alert.AlertType.ERROR, AlertHandler.ERROR, AlertHandler.DATABASE_ERROR, e.toString());
        }
    }

    private void initializeDatabase() {
        try {
            databaseConnection = new DatabaseConnection();
        } catch (ClassNotFoundException e) {
            new AlertHandler().display(Alert.AlertType.ERROR, AlertHandler.ERROR, AlertHandler.DATABASE_ERROR, e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainMenuButton.setOnAction(actionEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PATH_TO_MENU));
            ControllersHelper.changeScene(mainMenuButton, fxmlLoader);
        });
        replayButton.setOnAction(actionEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PATH_TO_REPLAY));
            ControllersHelper.changeSceneToMap(replayButton, fxmlLoader);
            ReplayController replayController = fxmlLoader.getController();
            replayController.setListOfTurns(listOfTurns);
        });
    }
}
