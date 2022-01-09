package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import replay.PlayerTurn;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ReplayController implements Initializable {

    private static final String RED_BUTTON_STYLE = "-fx-background-color:red;" + "-fx-border-color:black;";
    private static final String CYAN_BUTTON_STYLE = "-fx-background-color:cyan;" + "-fx-border-color:black;";
    private static final String BLUE_BUTTON_STYLE = "-fx-background-color:blue;" + "-fx-border-color:black;";
    private static final String ORANGE_BUTTON_STYLE = "-fx-background-color: #ffb31a;" + "-fx-border-color:black;";
    private static final String GREEN_BUTTON_STYLE = "-fx-background-color: #009900;" + "-fx-border-color:black;";
    private static final String LABEL_STYLE = "-fx-alignment:center;" + "-fx-font-size:20;" + "-fx-text-fill: white;" + "-fx-font-weight:bold;";
    private static final String YELLOW_BUTTON_STYLE = "-fx-background-color: #ffcc66;" + "-fx-border-color:black;";
    private static final String WHITE_TEXT = "-fx-text-fill: white;";
    private static final String BLACK_TEXT = "-fx-text-fill: black;";
    private static final String HIGHLIGHTED_TEXT = "-fx-text-fill: #ffcc66;";
    private static final String BOLD_TEXT = "-fx-font-weight: bold;" + "-fx-font-size: 15;";
    private final List<Label> listOfNameLabels;
    private final List<Label> listOfPointLabels;
    private final Button[][] buttonArray;
    private int actualTurn = 0;
    private List<String> listOfPlayers;
    private List<PlayerTurn> listOfTurns;
    private final HashMap<String, Label> playerPointsMap;
    private final HashMap<String, Label> playerNameLabelMap;
    private final List<Button> lastWordButtons;

    @FXML
    private AnchorPane buttonAnchorPane;

    @FXML
    private Button nextTurnButton;

    @FXML
    private Label turnFoldedLabel;
    @FXML
    private AnchorPane playersNamesAnchorPane;

    @FXML
    private AnchorPane pointsAnchorPane;

    @FXML
    private AnchorPane lettersAnchorPane;

    private List<Button> lettersButtonList;

    @FXML
    void showNextTurn(ActionEvent event) {
        refreshLabels();
        changeButtonsColorToDefault();
        lettersButtonList.clear();
        if (nextTurnButton.getText().equals("Rozpocznij"))
            nextTurnButton.setText("Następna tura");
        if (nextTurnButton.getText().equals("Wyjdz do menu!"))
            goToMainPage();
        if (actualTurn != listOfTurns.size() - 1) {
            highlightActualPlayer();
            PlayerTurn turn = listOfTurns.get(actualTurn);
            if (turn.isTurnFolded()) {
                turnFoldedLabel.setText("Gracz " + turn.getPlayerName() + " spasował");
            } else {
                turn.getListOfMoves().forEach(move -> {
                    final int x = move.getxCord();
                    final int y = move.getyCord();
                    buttonArray[x][y].setText(move.getLetter());

                });
                for (int i = 0; i < turn.getLetters().size(); i++) {
                    Button button = new Button();
                    button.setPrefSize(70, 60);
                    button.setLayoutX(i * 70);
                    button.setText(turn.getLetters().get(i).toUpperCase());
                    lettersButtonList.add(button);
                }
                lettersAnchorPane.getChildren().clear();
                lettersAnchorPane.getChildren().addAll(lettersButtonList);
                lettersAnchorPane.setLayoutX(600 - turn.getLetters().size() * 35);
                changeColorOfLastWord(turn);
            }
            actualTurn++;
        } else
            nextTurnButton.setText("Wyjdz do menu!");

    }

    public ReplayController() {
        listOfNameLabels = new ArrayList<>();
        playerNameLabelMap = new HashMap<>();
        listOfPointLabels = new ArrayList<>();
        playerPointsMap = new HashMap<>();
        buttonArray = new Button[15][15];
        lastWordButtons = new ArrayList<>();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createButtons();
        lettersButtonList = new ArrayList<>();
    }

    private void highlightActualPlayer() {
        Label label = playerNameLabelMap.get(listOfTurns.get(actualTurn).getPlayerName());
        label.styleProperty().setValue(label.getStyle() + HIGHLIGHTED_TEXT);
    }

    private void changeColorOfLastWord(PlayerTurn turn) {
        if (turn.getFirstXCord() == turn.getSecondXCord()) {
            int a = Math.min(turn.getFirstYCord(), turn.getSecondYCord());
            int b = Math.max(turn.getFirstYCord(), turn.getSecondYCord());
            for (int i = a; i <= b; i++)
                lastWordButtons.add(buttonArray[turn.getSecondXCord()][i]);
        } else {
            int a = Math.min(turn.getFirstXCord(), turn.getSecondXCord());
            int b = Math.max(turn.getFirstXCord(), turn.getSecondXCord());
            for (int i = a; i <= b; i++)
                lastWordButtons.add(buttonArray[i][turn.getSecondYCord()]);
        }
        lastWordButtons.forEach(button -> button.styleProperty().setValue(button.getStyle() + WHITE_TEXT));
        String actualPlayerName = turn.getPlayerName();
        playerPointsMap.get(actualPlayerName).setText(String.valueOf(turn.getPoints()));
    }

    private void changeButtonsColorToDefault() {
        if (!lastWordButtons.isEmpty()) {
            lastWordButtons.forEach(button -> button.styleProperty().setValue(button.getStyle() + BLACK_TEXT));
            lastWordButtons.clear();
        }

    }

    private void createButtons() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                buttonArray[i][j] = new Button();
                setButtonProperties(buttonArray[i][j], i, j);
                buttonAnchorPane.getChildren().add(buttonArray[i][j]);
            }
        }
        setRedColor();
        setCyanColor();
        setBlueColor();
        setOrangeColor();
        buttonArray[7][7].styleProperty().setValue(YELLOW_BUTTON_STYLE);
        setLetterStyle();
        nextTurnButton.setText("Rozpocznij");
    }

    private void fillNameAnchorPane(List<Label> listOfLabels, HashMap<String, Label> labelHashMap, AnchorPane anchorPane) {
        Label label;
        for (int i = 0; i < listOfPlayers.size(); i++) {
            label = new Label();
            label.setText(listOfPlayers.get(i));
            label.setPrefSize(256, 40);
            label.setLayoutX(0);
            label.setLayoutY(i * 40);
            label.styleProperty().setValue(LABEL_STYLE);
            listOfLabels.add(label);
            anchorPane.getChildren().add(label);
            labelHashMap.put(listOfPlayers.get(i), label);
        }
    }

    private void fillPointsAnchorPane(List<Label> listOfLabels, HashMap<String, Label> labelHashMap, AnchorPane anchorPane) {
        Label labelForName, labelForPoints;
        for (int i = 0; i < listOfPlayers.size(); i++) {
            labelForName = new Label();
            labelForPoints = new Label();
            labelForName.setText(listOfPlayers.get(i));
            labelForName.setPrefSize(128, 40);
            labelForName.setLayoutX(0);
            labelForName.setLayoutY(i * 40);
            labelForPoints.setText("0");
            labelForPoints.setPrefSize(128, 40);
            labelForPoints.setLayoutX(128);
            labelForPoints.setLayoutY(i * 40);
            labelForName.styleProperty().setValue(LABEL_STYLE);
            labelForPoints.styleProperty().setValue(LABEL_STYLE);
            listOfLabels.add(labelForPoints);
            labelHashMap.put(listOfPlayers.get(i), labelForPoints);
            anchorPane.getChildren().addAll(labelForName, labelForPoints);
        }
    }

    private void goToMainPage() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Menu.fxml"));
        ControllersHelper.changeScene(nextTurnButton, fxmlLoader);
    }

    private void setButtonProperties(Button button, int i, int j) {
        button.setLayoutX(j * 40);
        button.setLayoutY(i * 40);
        button.setPrefSize(40, 40);
        button.styleProperty().setValue(GREEN_BUTTON_STYLE);
    }

    private void setLetterStyle() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                buttonArray[i][j].setStyle(buttonArray[i][j].getStyle() + BOLD_TEXT);
            }
        }
    }

    private void refreshLabels() {
        listOfNameLabels.forEach(label -> label.styleProperty().setValue(label.getStyle() + WHITE_TEXT));
        turnFoldedLabel.setText("");
    }

    private void setRedColor() {
        buttonArray[14][0].styleProperty().setValue(RED_BUTTON_STYLE);
        buttonArray[14][7].styleProperty().setValue(RED_BUTTON_STYLE);
        buttonArray[14][14].styleProperty().setValue(RED_BUTTON_STYLE);
        buttonArray[0][0].styleProperty().setValue(RED_BUTTON_STYLE);
        buttonArray[7][0].styleProperty().setValue(RED_BUTTON_STYLE);
        buttonArray[7][14].styleProperty().setValue(RED_BUTTON_STYLE);
        buttonArray[0][14].styleProperty().setValue(RED_BUTTON_STYLE);
        buttonArray[0][7].styleProperty().setValue(RED_BUTTON_STYLE);
    }

    private void setCyanColor() {
        buttonArray[0][3].styleProperty().setValue(CYAN_BUTTON_STYLE);
        buttonArray[0][11].styleProperty().setValue(CYAN_BUTTON_STYLE);
        buttonArray[2][6].styleProperty().setValue(CYAN_BUTTON_STYLE);
        buttonArray[2][8].styleProperty().setValue(CYAN_BUTTON_STYLE);
        buttonArray[3][0].styleProperty().setValue(CYAN_BUTTON_STYLE);
        buttonArray[3][7].styleProperty().setValue(CYAN_BUTTON_STYLE);
        buttonArray[3][14].styleProperty().setValue(CYAN_BUTTON_STYLE);
        buttonArray[6][2].styleProperty().setValue(CYAN_BUTTON_STYLE);
        buttonArray[6][6].styleProperty().setValue(CYAN_BUTTON_STYLE);
        buttonArray[6][8].styleProperty().setValue(CYAN_BUTTON_STYLE);
        buttonArray[6][12].styleProperty().setValue(CYAN_BUTTON_STYLE);
        buttonArray[7][3].styleProperty().setValue(CYAN_BUTTON_STYLE);
        buttonArray[7][11].styleProperty().setValue(CYAN_BUTTON_STYLE);
        buttonArray[8][2].styleProperty().setValue(CYAN_BUTTON_STYLE);
        buttonArray[8][6].styleProperty().setValue(CYAN_BUTTON_STYLE);
        buttonArray[8][8].styleProperty().setValue(CYAN_BUTTON_STYLE);
        buttonArray[8][12].styleProperty().setValue(CYAN_BUTTON_STYLE);
        buttonArray[11][0].styleProperty().setValue(CYAN_BUTTON_STYLE);
        buttonArray[11][7].styleProperty().setValue(CYAN_BUTTON_STYLE);
        buttonArray[11][14].styleProperty().setValue(CYAN_BUTTON_STYLE);
        buttonArray[12][6].styleProperty().setValue(CYAN_BUTTON_STYLE);
        buttonArray[12][8].styleProperty().setValue(CYAN_BUTTON_STYLE);
        buttonArray[14][3].styleProperty().setValue(CYAN_BUTTON_STYLE);
        buttonArray[14][11].styleProperty().setValue(CYAN_BUTTON_STYLE);
    }

    private void setBlueColor() {
        buttonArray[1][5].styleProperty().setValue(BLUE_BUTTON_STYLE);
        buttonArray[1][9].styleProperty().setValue(BLUE_BUTTON_STYLE);
        buttonArray[5][1].styleProperty().setValue(BLUE_BUTTON_STYLE);
        buttonArray[5][5].styleProperty().setValue(BLUE_BUTTON_STYLE);
        buttonArray[5][9].styleProperty().setValue(BLUE_BUTTON_STYLE);
        buttonArray[5][13].styleProperty().setValue(BLUE_BUTTON_STYLE);
        buttonArray[9][1].styleProperty().setValue(BLUE_BUTTON_STYLE);
        buttonArray[9][5].styleProperty().setValue(BLUE_BUTTON_STYLE);
        buttonArray[9][9].styleProperty().setValue(BLUE_BUTTON_STYLE);
        buttonArray[9][13].styleProperty().setValue(BLUE_BUTTON_STYLE);
        buttonArray[13][5].styleProperty().setValue(BLUE_BUTTON_STYLE);
        buttonArray[13][9].styleProperty().setValue(BLUE_BUTTON_STYLE);
    }

    private void setOrangeColor() {
        buttonArray[1][1].styleProperty().setValue(ORANGE_BUTTON_STYLE);
        buttonArray[2][2].styleProperty().setValue(ORANGE_BUTTON_STYLE);
        buttonArray[3][3].styleProperty().setValue(ORANGE_BUTTON_STYLE);
        buttonArray[4][4].styleProperty().setValue(ORANGE_BUTTON_STYLE);
        buttonArray[1][13].styleProperty().setValue(ORANGE_BUTTON_STYLE);
        buttonArray[2][12].styleProperty().setValue(ORANGE_BUTTON_STYLE);
        buttonArray[3][11].styleProperty().setValue(ORANGE_BUTTON_STYLE);
        buttonArray[4][10].styleProperty().setValue(ORANGE_BUTTON_STYLE);
        buttonArray[13][1].styleProperty().setValue(ORANGE_BUTTON_STYLE);
        buttonArray[12][2].styleProperty().setValue(ORANGE_BUTTON_STYLE);
        buttonArray[11][3].styleProperty().setValue(ORANGE_BUTTON_STYLE);
        buttonArray[10][4].styleProperty().setValue(ORANGE_BUTTON_STYLE);
        buttonArray[10][10].styleProperty().setValue(ORANGE_BUTTON_STYLE);
        buttonArray[11][11].styleProperty().setValue(ORANGE_BUTTON_STYLE);
        buttonArray[12][12].styleProperty().setValue(ORANGE_BUTTON_STYLE);
        buttonArray[13][13].styleProperty().setValue(ORANGE_BUTTON_STYLE);

    }

    public void setListOfTurns(List<PlayerTurn> listOfTurns) {
        this.listOfTurns = listOfTurns;
        listOfPlayers = listOfTurns.stream().map(PlayerTurn::getPlayerName).distinct().collect(Collectors.toList());
        fillNameAnchorPane(listOfNameLabels, playerNameLabelMap, playersNamesAnchorPane);
        fillPointsAnchorPane(listOfPointLabels, playerPointsMap, pointsAnchorPane);
    }

}
