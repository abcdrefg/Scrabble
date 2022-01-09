package gui;

import Game.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AIMenuController implements Initializable {

    private static final String PATH_TO_MENU = "fxml/Menu.fxml";
    private static final String PATH_TO_MAP = "fxml/Map.fxml";
    private static final String SELECT_AI_LEVEL = "Wybierz poziom trudności!";
    private static final String COMPUTER_NR = "Komputer nr. ";
    @FXML
    private Button playWithAIButton;

    @FXML
    private Button returnButton;

    @FXML
    private ComboBox<Integer> numberOfAICombobox;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Label mainLabel;

    private List<ComboBox<String>> listOfAIDiffCombobox;

    private int aiIterator = 0;

    @FXML
    void returnToMenuPage(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PATH_TO_MENU));
        ControllersHelper.changeScene(returnButton, fxmlLoader);
    }

    @FXML
    void startGameWithAI(ActionEvent event) {
        if (listOfAIDiffCombobox != null) {
            boolean areLevelsSelected = listOfAIDiffCombobox.stream().anyMatch(comboBox -> comboBox.getSelectionModel().isEmpty());
            if (!areLevelsSelected) {
                List<Player> listOfAIPlayers = new ArrayList<>();
                aiIterator = 1;
                listOfAIDiffCombobox.forEach(comboBox -> {
                    Player aiPlayer = new Player(false);
                    String aiLevel = comboBox.getSelectionModel().getSelectedItem();
                    aiPlayer.setPlayerName("Komputer " + aiIterator + " " + aiLevel);
                    aiPlayer.setAiPlayerLevel(aiLevel);
                    listOfAIPlayers.add(aiPlayer);
                    aiIterator++;
                });
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PATH_TO_MAP));
                ControllersHelper.changeSceneToMap(playWithAIButton, fxmlLoader);
                MapController mapController = fxmlLoader.getController();
                mapController.setPlayers(listOfAIPlayers);
            } else
                new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.WARNING, "Nie dobrano poziomów trudności!", "Uzupełnij wszystkie poziomy trudności!");

        } else {
            new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.WARNING, "Zła ilość graczy AI", "Wybierz ilość oraz poziom trudności wszystkich graczy!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numberOfAICombobox.getItems().addAll(2, 3, 4);
        numberOfAICombobox.setOnAction(actionEvent -> createAIDifficultySelectors(numberOfAICombobox.getSelectionModel().getSelectedItem()));
    }

    public void createAIDifficultySelectors(int numberOfAI) {
        listOfAIDiffCombobox = new ArrayList<>();
        List<Label> listOfLabels = new ArrayList<>();
        for (int i = 0; i < numberOfAI; i++) {
            listOfAIDiffCombobox.add(ControllersHelper.initCombobox(70, 60 + i * (30 + 30)));
            listOfLabels.add(ControllersHelper.initLabel(COMPUTER_NR + (i + 1), 0, 30 + i * (30 + 30), 300, 30));
        }
        mainAnchorPane.getChildren().remove(numberOfAICombobox);
        mainLabel.setText(SELECT_AI_LEVEL);
        mainAnchorPane.getChildren().addAll(listOfAIDiffCombobox);
        mainAnchorPane.getChildren().addAll(listOfLabels);
    }

}
