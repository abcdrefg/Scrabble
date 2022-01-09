package gui;

import Game.Player;
import User.User;
import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class SelectPlayersController implements Initializable {

    private static final String FOURTH_PLAYER = "FOURTH PLAYER";
    private static final String THIRD_PLAYER = "THIRD PLAYER";
    private static final String SECOND_PLAYER = "SECOND PLAYER";
    private static final String PATH_TO_MENU = "fxml/Menu.fxml";
    private static final String LOG_IN = "Zaloguj!";
    private static final String DISABLED_COMBOBOX_STYLE = "-fx-opacity: 1;";
    private static final String PLAYER = "Gracz";
    private static final String COMPUTER = "Komputer";
    private static User user;
    private static DatabaseConnection databaseConnection;
    private final List<Player> playerList = new ArrayList<>();

    @FXML
    private Button playButton;

    @FXML
    private Label loggedPlayerLabel;

    @FXML
    private AnchorPane secondPlayerPane;

    @FXML
    private ComboBox<String> secondPlayerCombobox;

    @FXML
    private Button addSecondPlayerButton;

    @FXML
    private ComboBox<String> fourthPlayerCombobox;

    @FXML
    private Button addFourthPlayerButton;

    @FXML
    private ComboBox<String> thirdPlayerCombobox;

    @FXML
    private Button addThirdPlayerButton;

    @FXML
    private Button returnButton;

    @FXML
    private AnchorPane thirdPlayerPane;

    @FXML
    private AnchorPane fourthPlayerPane;

    @FXML
    private AnchorPane mainAnchorPane;

    private TextField secondPlayerLoginTF;
    private TextField thirdPlayerLoginTF;
    private TextField fourthPlayerLoginTF;

    private PasswordField secondPlayerPasswordPF;
    private PasswordField thirdPlayerPasswordPF;
    private PasswordField fourthPlayerPasswordPF;

    private ComboBox<String> secondAILevelCombobox;
    private ComboBox<String> thirdAILevelCombobox;
    private ComboBox<String> fourthAILevelCombobox;

    private TextField gameDurationMinutesTextField;
    private TextField gameDurationSecondsTextField;
    private TextField turnDurationMinutesTextField;
    private TextField turnDurationSecondsTextField;

    private int numberOfAI = 0;

    @FXML
    void returnToMenuPage(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PATH_TO_MENU));
        ControllersHelper.changeScene(returnButton, fxmlLoader);
    }

    @FXML
    void startGame(ActionEvent event) {
        if (playerList.size() > 1) {
            mainAnchorPane.getChildren().clear();
            gameDurationMinutesTextField = new TextField("15");
            gameDurationSecondsTextField = new TextField("0");
            turnDurationMinutesTextField = new TextField("1");
            turnDurationSecondsTextField = new TextField("0");
            createGameDurationTextFields();
            createGameDurationLabels();
            playButton.setOnAction(actionEvent -> {
                validateGameDurationTimes();
            });
        } else {
            new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.WARNING, AlertHandler.NOT_ENOUGH_PLAYERS, AlertHandler.NOT_ENOUGH_PLAYERS_TO_START_GAME);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        secondPlayerCombobox.getItems().addAll(COMPUTER, PLAYER);
        thirdPlayerCombobox.getItems().addAll(COMPUTER, PLAYER);
        fourthPlayerCombobox.getItems().addAll(COMPUTER, PLAYER);
        addPlayerButtonAction(addFourthPlayerButton, fourthPlayerCombobox, fourthPlayerPane, FOURTH_PLAYER);
        addPlayerButtonAction(addThirdPlayerButton, thirdPlayerCombobox, thirdPlayerPane, THIRD_PLAYER);
        addPlayerButtonAction(addSecondPlayerButton, secondPlayerCombobox, secondPlayerPane, SECOND_PLAYER);
        initializeDatabase();
    }

    private void validateGameDurationTimes() {
        boolean gameDurationCondition = gameDurationMinutesTextField.getText().isEmpty() || gameDurationSecondsTextField.getText().isEmpty();
        boolean turnDurationCondition = turnDurationMinutesTextField.getText().isEmpty() || turnDurationSecondsTextField.getText().isEmpty();
        if (!(gameDurationCondition || turnDurationCondition)) {
            int gameDurationInSeconds = Integer.parseInt(gameDurationMinutesTextField.getText()) * 60
                    + Integer.parseInt(gameDurationSecondsTextField.getText());
            int turnDurationInSeconds = Integer.parseInt(turnDurationMinutesTextField.getText()) * 60
                    + Integer.parseInt(turnDurationSecondsTextField.getText());
            if (gameDurationInSeconds < turnDurationInSeconds) {
                new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.WARNING, "Źle dobrane czasy rozgrywki!", "Czas trwania jednej tury jest dłuższy niż całej rozgrywki!");
                gameDurationSecondsTextField.setText("");
                gameDurationMinutesTextField.setText("");
                turnDurationSecondsTextField.setText("");
                turnDurationMinutesTextField.setText("");
            } else {
                long gameDurationMinutes = Long.parseLong(gameDurationMinutesTextField.getText());
                long gameDurationSeconds = Long.parseLong(gameDurationSecondsTextField.getText());
                long turnDurationMinutes = Long.parseLong(turnDurationMinutesTextField.getText());
                long turnDurationSeconds = Long.parseLong(turnDurationSecondsTextField.getText());
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Map.fxml"));
                ControllersHelper.changeSceneToMap(playButton, fxmlLoader);
                MapController mapController = fxmlLoader.getController();
                mapController.setPlayers(playerList);
                mapController.setTimes(gameDurationMinutes, gameDurationSeconds, turnDurationMinutes, turnDurationSeconds);
            }
        } else {
            new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.WARNING, "Źle dobrane czasy rozgywki!", "Uzupełnij wszystkie pola!");
        }
    }

    private void createGameDurationTextFields() {
        prepareGameDurationTextFields(gameDurationMinutesTextField, 140, 140, 20);
        prepareGameDurationTextFields(gameDurationSecondsTextField, 140, 200, 60);
        prepareGameDurationTextFields(turnDurationMinutesTextField, 490, 140, 20);
        prepareGameDurationTextFields(turnDurationSecondsTextField, 490, 200, 60);
        mainAnchorPane.getChildren().addAll(gameDurationMinutesTextField, gameDurationSecondsTextField, turnDurationMinutesTextField, turnDurationSecondsTextField);
    }

    private void prepareGameDurationTextFields(TextField gameDurationTextField, int layoutX, int layoutY, int maxValue) {
        gameDurationTextField.textProperty().addListener((observableValue, oldValue, newValue) -> checkGameAndTurnDurationTextFields(oldValue, newValue, gameDurationTextField, maxValue));
        gameDurationTextField.setPrefSize(35, 25);
        gameDurationTextField.setLayoutX(layoutX);
        gameDurationTextField.setLayoutY(layoutY);
    }

    private void createGameDurationLabels() {
        Label mainLabel = ControllersHelper.initLabel("Dobierz czas rozgrywki!", 185, 15, 235, 30);
        Label totalTimeLabel = ControllersHelper.initLabel("Czas całej gry", 15, 100, 200, 30);
        Label totalTimeMinutesLabel = ControllersHelper.initLabel("Minuty", 20, 140, 100, 30);
        Label totalTimeSecondsLabel = ControllersHelper.initLabel("Sekundy", 20, 200, 100, 30);
        Label turnTimeLabel = ControllersHelper.initLabel("Czas tury gracza", 350, 100, 200, 30);
        Label turnTimeMinutesLabel = ControllersHelper.initLabel("Minuty", 360, 140, 100, 30);
        Label turnTimeSecondsLabel = ControllersHelper.initLabel("Sekundy", 360, 200, 100, 30);
        mainAnchorPane.getChildren().addAll(mainLabel, totalTimeLabel, totalTimeMinutesLabel, totalTimeSecondsLabel, turnTimeLabel, turnTimeMinutesLabel, turnTimeSecondsLabel);
    }

    private void checkGameAndTurnDurationTextFields(String oldValue, String newValue, TextField durationTextField, int maxValue) {
        if (!newValue.matches("\\d{0,2}"))
            durationTextField.setText(oldValue);
        else if (newValue.equals("00"))
            durationTextField.setText("0");
        else if (!newValue.equals("")) {
            if (Integer.parseInt(newValue) > maxValue)
                durationTextField.setText(Integer.toString(maxValue));
        }
    }

    private void addPlayerButtonAction(Button addPlayerButton, ComboBox<String> playerCombobox, AnchorPane playerPane, String playerNumber) {
        addPlayerButton.setOnAction(actionEvent -> {
            if (playerCombobox.getSelectionModel().isEmpty()) {
                new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.WARNING, AlertHandler.PLAYER_TYPE_NOT_SELECTED, AlertHandler.CHOOSE_PLAYER_TYPE);
            } else if (playerCombobox.getSelectionModel().getSelectedItem().equals(PLAYER)) {
                playerPane.getChildren().removeAll(playerCombobox);
                switch (playerNumber) {
                    case SECOND_PLAYER -> changeComponentsForSecondPlayerSelection();
                    case THIRD_PLAYER -> changeComponentsForThirdPlayerSelection();
                    case FOURTH_PLAYER -> changeComponentsForFourthPlayerSelection();
                }
            } else if (playerCombobox.getSelectionModel().getSelectedItem().equals(COMPUTER)) {
                playerCombobox.setDisable(true);
                playerCombobox.styleProperty().setValue(DISABLED_COMBOBOX_STYLE);
                switch (playerNumber) {
                    case SECOND_PLAYER -> changeComponentsForSecondComputerSelection();
                    case THIRD_PLAYER -> changeComponentsForThirdComputerSelection();
                    case FOURTH_PLAYER -> changeComponentsForFourthComputerSelection();
                }
            }
        });
    }


    public void setUser(User user) {
        SelectPlayersController.user = user;
        loggedPlayerLabel.setText(user.getLogin());
        Player player = new Player(true);
        player.setPlayerName(SelectPlayersController.user.getLogin());
        playerList.add(player);
    }

    private void changeComponentsForSecondPlayerSelection() {
        addSecondPlayerButton.setOnAction(actionEvent -> checkUserPresence(secondPlayerLoginTF, secondPlayerPasswordPF, secondPlayerPane, addSecondPlayerButton));
        addSecondPlayerButton.setText(LOG_IN);
        secondPlayerLoginTF = ControllersHelper.initLoginTextField();
        secondPlayerPasswordPF = ControllersHelper.initPasswordField();
        secondPlayerPane.getChildren().addAll(secondPlayerLoginTF, secondPlayerPasswordPF);
    }

    private void changeComponentsForThirdPlayerSelection() {
        addThirdPlayerButton.setOnAction(actionEvent -> checkUserPresence(thirdPlayerLoginTF, thirdPlayerPasswordPF, thirdPlayerPane, addThirdPlayerButton));
        addThirdPlayerButton.setText(LOG_IN);
        thirdPlayerLoginTF = ControllersHelper.initLoginTextField();
        thirdPlayerPasswordPF = ControllersHelper.initPasswordField();
        thirdPlayerPane.getChildren().addAll(thirdPlayerLoginTF, thirdPlayerPasswordPF);
    }

    private void changeComponentsForFourthPlayerSelection() {
        addFourthPlayerButton.setOnAction(actionEvent -> checkUserPresence(fourthPlayerLoginTF, fourthPlayerPasswordPF, fourthPlayerPane, addFourthPlayerButton));
        addFourthPlayerButton.setText(LOG_IN);
        fourthPlayerLoginTF = ControllersHelper.initLoginTextField();
        fourthPlayerPasswordPF = ControllersHelper.initPasswordField();
        fourthPlayerPane.getChildren().addAll(fourthPlayerLoginTF, fourthPlayerPasswordPF);
    }

    private void changeComponentsForSecondComputerSelection() {
        addSecondPlayerButton.setOnAction(actionEvent -> createAIPlayer(secondPlayerCombobox, secondAILevelCombobox, secondPlayerPane, addSecondPlayerButton));
        secondAILevelCombobox = ControllersHelper.initCombobox();
        secondPlayerPane.getChildren().addAll(secondAILevelCombobox);
    }

    private void changeComponentsForThirdComputerSelection() {
        addThirdPlayerButton.setOnAction(actionEvent -> createAIPlayer(thirdPlayerCombobox, thirdAILevelCombobox, thirdPlayerPane, addThirdPlayerButton));
        thirdAILevelCombobox = ControllersHelper.initCombobox();
        thirdPlayerPane.getChildren().addAll(thirdAILevelCombobox);
    }

    private void changeComponentsForFourthComputerSelection() {
        addFourthPlayerButton.setOnAction(actionEvent -> createAIPlayer(fourthPlayerCombobox, fourthAILevelCombobox, fourthPlayerPane, addFourthPlayerButton));
        fourthAILevelCombobox = ControllersHelper.initCombobox();
        fourthPlayerPane.getChildren().addAll(fourthAILevelCombobox);
    }

    private void checkUserPresence(TextField loginTextField, PasswordField passwordField, AnchorPane anchorPane, Button button) {
        String login = loginTextField.getText();
        String password = passwordField.getText();
        boolean isPlayerLogged = playerList.stream().filter(Player::isItHuman).anyMatch(player -> player.getPlayerName().equals(login));
        if (isPlayerLogged) {
            new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.WARNING, AlertHandler.PLAYER_IS_LOGGED, AlertHandler.USER_IS_ALREADY_LOGGED);
        } else {
            try {
                Optional<User> optionalUser = databaseConnection.getUserParams(login, password);
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    Player player = new Player(true);
                    player.setPlayerName(user.getLogin());
                    playerList.add(player);
                    anchorPane.getChildren().removeAll(loginTextField, passwordField, button);
                    anchorPane.getChildren().add(ControllersHelper.initLabel(login));
                } else {
                    new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.WARNING, AlertHandler.LOGIN_WARNING, AlertHandler.USER_DOES_NOT_EXIST);
                }
            } catch (SQLException e) {
                new AlertHandler().display(Alert.AlertType.ERROR, AlertHandler.ERROR, AlertHandler.DATABASE_ERROR, e.getMessage());
            }
        }

    }

    private void createAIPlayer(ComboBox<String> playerCombobox, ComboBox<String> AILevelCombobox, AnchorPane anchorPane, Button button) {
        if (AILevelCombobox.getSelectionModel().isEmpty()) {
            new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.WARNING, AlertHandler.DIFFICULTY_IS_NOT_SELECTED, AlertHandler.CHOOSE_COMPUTER_DIFFICULTY);
        } else {
            String level = AILevelCombobox.getSelectionModel().getSelectedItem();
            String aiPlayerName = "Komputer " + ++numberOfAI + " " + level;
            Player aiPlayer = new Player(false);
            aiPlayer.setAiPlayerLevel(level);
            aiPlayer.setPlayerName(aiPlayerName);
            playerList.add(aiPlayer);
            anchorPane.getChildren().removeAll(playerCombobox, AILevelCombobox, button);
            anchorPane.getChildren().add(ControllersHelper.initLabel(aiPlayerName));
        }
    }

    private void initializeDatabase() {
        try {
            databaseConnection = new DatabaseConnection();
        } catch (ClassNotFoundException e) {
            new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.ERROR, AlertHandler.DATABASE_ERROR, e.getMessage());
        }
    }

}
