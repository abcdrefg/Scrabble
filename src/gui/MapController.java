package gui;

import Game.Game;
import Game.Player;
import ScrabbleBoard.Board;
import ScrabbleBoard.Field;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import replay.PlayerTurn;
import wordFinder.PossibleWords;

import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MapController implements Initializable {
    @FXML
    private Button p0_0;

    @FXML
    private Button p1_0;

    @FXML
    private Button p2_0;

    @FXML
    private Button p3_0;

    @FXML
    private Button p4_0;

    @FXML
    private Button p5_0;

    @FXML
    private Button p6_0;

    @FXML
    private Button p7_0;

    @FXML
    private Button p8_0;

    @FXML
    private Button p9_0;

    @FXML
    private Button p10_0;

    @FXML
    private Button p11_0;

    @FXML
    private Button p12_0;

    @FXML
    private Button p13_0;

    @FXML
    private Button p14_0;

    @FXML
    private Button p0_1;

    @FXML
    private Button p1_1;

    @FXML
    private Button p2_1;

    @FXML
    private Button p3_1;

    @FXML
    private Button p4_1;

    @FXML
    private Button p5_1;

    @FXML
    private Button p6_1;

    @FXML
    private Button p7_1;

    @FXML
    private Button p8_1;

    @FXML
    private Button p9_1;

    @FXML
    private Button p10_1;

    @FXML
    private Button p11_1;

    @FXML
    private Button p12_1;

    @FXML
    private Button p13_1;

    @FXML
    private Button p14_1;

    @FXML
    private Button p0_2;

    @FXML
    private Button p1_2;

    @FXML
    private Button p2_2;

    @FXML
    private Button p3_2;

    @FXML
    private Button p4_2;

    @FXML
    private Button p5_2;

    @FXML
    private Button p6_2;

    @FXML
    private Button p7_2;

    @FXML
    private Button p8_2;

    @FXML
    private Button p9_2;

    @FXML
    private Button p10_2;

    @FXML
    private Button p11_2;

    @FXML
    private Button p12_2;

    @FXML
    private Button p13_2;

    @FXML
    private Button p14_2;

    @FXML
    private Button p0_3;

    @FXML
    private Button p1_3;

    @FXML
    private Button p2_3;

    @FXML
    private Button p3_3;

    @FXML
    private Button p4_3;

    @FXML
    private Button p5_3;

    @FXML
    private Button p6_3;

    @FXML
    private Button p7_3;

    @FXML
    private Button p8_3;

    @FXML
    private Button p9_3;

    @FXML
    private Button p10_3;

    @FXML
    private Button p11_3;

    @FXML
    private Button p12_3;

    @FXML
    private Button p13_3;

    @FXML
    private Button p14_3;

    @FXML
    private Button p0_4;

    @FXML
    private Button p1_4;

    @FXML
    private Button p2_4;

    @FXML
    private Button p3_4;

    @FXML
    private Button p4_4;

    @FXML
    private Button p5_4;

    @FXML
    private Button p6_4;

    @FXML
    private Button p7_4;

    @FXML
    private Button p8_4;

    @FXML
    private Button p9_4;

    @FXML
    private Button p10_4;

    @FXML
    private Button p11_4;

    @FXML
    private Button p12_4;

    @FXML
    private Button p13_4;

    @FXML
    private Button p14_4;

    @FXML
    private Button p0_5;

    @FXML
    private Button p1_5;

    @FXML
    private Button p2_5;

    @FXML
    private Button p3_5;

    @FXML
    private Button p4_5;

    @FXML
    private Button p5_5;

    @FXML
    private Button p6_5;

    @FXML
    private Button p7_5;

    @FXML
    private Button p8_5;

    @FXML
    private Button p9_5;

    @FXML
    private Button p10_5;

    @FXML
    private Button p11_5;

    @FXML
    private Button p12_5;

    @FXML
    private Button p13_5;

    @FXML
    private Button p14_5;

    @FXML
    private Button p0_6;

    @FXML
    private Button p1_6;

    @FXML
    private Button p2_6;

    @FXML
    private Button p3_6;

    @FXML
    private Button p4_6;

    @FXML
    private Button p5_6;

    @FXML
    private Button p6_6;

    @FXML
    private Button p7_6;

    @FXML
    private Button p8_6;

    @FXML
    private Button p9_6;

    @FXML
    private Button p10_6;

    @FXML
    private Button p11_6;

    @FXML
    private Button p12_6;

    @FXML
    private Button p13_6;

    @FXML
    private Button p14_6;

    @FXML
    private Button p0_7;

    @FXML
    private Button p1_7;

    @FXML
    private Button p2_7;

    @FXML
    private Button p3_7;

    @FXML
    private Button p4_7;

    @FXML
    private Button p5_7;

    @FXML
    private Button p6_7;

    @FXML
    private Button p7_7;

    @FXML
    private Button p8_7;

    @FXML
    private Button p9_7;

    @FXML
    private Button p10_7;

    @FXML
    private Button p11_7;

    @FXML
    private Button p12_7;

    @FXML
    private Button p13_7;

    @FXML
    private Button p14_7;

    @FXML
    private Button p0_8;

    @FXML
    private Button p1_8;

    @FXML
    private Button p2_8;

    @FXML
    private Button p3_8;

    @FXML
    private Button p4_8;

    @FXML
    private Button p5_8;

    @FXML
    private Button p6_8;

    @FXML
    private Button p7_8;

    @FXML
    private Button p8_8;

    @FXML
    private Button p9_8;

    @FXML
    private Button p10_8;

    @FXML
    private Button p11_8;

    @FXML
    private Button p12_8;

    @FXML
    private Button p13_8;

    @FXML
    private Button p14_8;

    @FXML
    private Button p0_9;

    @FXML
    private Button p1_9;

    @FXML
    private Button p2_9;

    @FXML
    private Button p3_9;

    @FXML
    private Button p4_9;

    @FXML
    private Button p5_9;

    @FXML
    private Button p6_9;

    @FXML
    private Button p7_9;

    @FXML
    private Button p8_9;

    @FXML
    private Button p9_9;

    @FXML
    private Button p10_9;

    @FXML
    private Button p11_9;

    @FXML
    private Button p12_9;

    @FXML
    private Button p13_9;

    @FXML
    private Button p14_9;

    @FXML
    private Button p0_10;

    @FXML
    private Button p1_10;

    @FXML
    private Button p2_10;

    @FXML
    private Button p3_10;

    @FXML
    private Button p4_10;

    @FXML
    private Button p5_10;

    @FXML
    private Button p6_10;

    @FXML
    private Button p7_10;

    @FXML
    private Button p8_10;

    @FXML
    private Button p9_10;

    @FXML
    private Button p10_10;

    @FXML
    private Button p11_10;

    @FXML
    private Button p12_10;

    @FXML
    private Button p13_10;

    @FXML
    private Button p14_10;

    @FXML
    private Button p0_11;

    @FXML
    private Button p1_11;

    @FXML
    private Button p2_11;

    @FXML
    private Button p3_11;

    @FXML
    private Button p4_11;

    @FXML
    private Button p5_11;

    @FXML
    private Button p6_11;

    @FXML
    private Button p7_11;

    @FXML
    private Button p8_11;

    @FXML
    private Button p9_11;

    @FXML
    private Button p10_11;

    @FXML
    private Button p11_11;

    @FXML
    private Button p12_11;

    @FXML
    private Button p13_11;

    @FXML
    private Button p14_11;

    @FXML
    private Button p0_12;

    @FXML
    private Button p1_12;

    @FXML
    private Button p2_12;

    @FXML
    private Button p3_12;

    @FXML
    private Button p4_12;

    @FXML
    private Button p5_12;

    @FXML
    private Button p6_12;

    @FXML
    private Button p7_12;

    @FXML
    private Button p8_12;

    @FXML
    private Button p9_12;

    @FXML
    private Button p10_12;

    @FXML
    private Button p11_12;

    @FXML
    private Button p12_12;

    @FXML
    private Button p13_12;

    @FXML
    private Button p14_12;

    @FXML
    private Button p0_13;

    @FXML
    private Button p1_13;

    @FXML
    private Button p2_13;

    @FXML
    private Button p3_13;

    @FXML
    private Button p4_13;

    @FXML
    private Button p5_13;

    @FXML
    private Button p6_13;

    @FXML
    private Button p7_13;

    @FXML
    private Button p8_13;

    @FXML
    private Button p9_13;

    @FXML
    private Button p10_13;

    @FXML
    private Button p11_13;

    @FXML
    private Button p12_13;

    @FXML
    private Button p13_13;

    @FXML
    private Button p14_13;

    @FXML
    private Button p0_14;

    @FXML
    private Button p1_14;

    @FXML
    private Button p2_14;

    @FXML
    private Button p3_14;

    @FXML
    private Button p4_14;

    @FXML
    private Button p5_14;

    @FXML
    private Button p6_14;

    @FXML
    private Button p7_14;

    @FXML
    private Button p8_14;

    @FXML
    private Button p9_14;

    @FXML
    private Button p10_14;

    @FXML
    private Button p11_14;

    @FXML
    private Button p12_14;

    @FXML
    private Button p13_14;

    @FXML
    private Button p14_14;

    @FXML
    private Button Letter1;

    @FXML
    private Button Letter2;

    @FXML
    private Button Letter3;

    @FXML
    private Button Letter4;

    @FXML
    private Button Letter5;

    @FXML
    private Button Letter6;

    @FXML
    private Button Letter7;

    @FXML
    private Label TurnP1;

    @FXML
    private Label TurnP2;

    @FXML
    private Label TurnP3;

    @FXML
    private Label TurnP4;

    @FXML
    private Label PointsP1;

    @FXML
    private Label PointsP2;

    @FXML
    private Label PointsP3;

    @FXML
    private Label PointsP4;

    @FXML
    private Button SubmitButton;

    @FXML
    private Button ChangeButton;

    @FXML
    private Button Revert;

    @FXML
    private Label P1Label;

    @FXML
    private Label P2Label;

    @FXML
    private Label P3Label;

    @FXML
    private Label P4Label;

    @FXML
    private Label turnTimeLbl;

    @FXML
    private Label turnTimeLbl1;

    private ArrayList<ArrayList<Button>> guiMap = new ArrayList<>();
    private List<Button> alreadyUsedLetters = new ArrayList<>();
    private Board board;
    private ArrayList<GuiMove> movesList = new ArrayList<>();
    private Button defaultButton = new Button();
    private Button currentButton = defaultButton;
    private Board tempBoard;
    private ArrayList<Button> playerLetter = new ArrayList<>();
    private Game game;
    private List<Player> players = new ArrayList<>();
    private Integer currentPlayer = 0;
    private ArrayList<Label> playerTurn = new ArrayList<>();
    private ArrayList<Label> playerPoints1 = new ArrayList<>();
    private ArrayList<Label> playerPoints2 = new ArrayList<>();
    private final String labelStyle = "-fx-alignment: center;" +
            "-fx-font-size: 20; " +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;";
    private final String currentLabelStyle = "-fx-alignment: center;" +
            "-fx-font-size: 20; " +
            "-fx-text-fill:  #ffcc66;" +
            "-fx-font-weight: bold;";
    private ArrayList<PlayerTurn> movesLog = new ArrayList<>();
    private long gameTime = 180;
    private long turnTime = 20;
    private LocalTime startTime = LocalTime.of(0, 0, 0);
    private LocalTime startTimeGlobal = LocalTime.of(0, 0, 0);
    private Timeline clockTurn;
    private Timeline clockGame;
    private long turnSeconds = 59;
    private long turnMinutes = 59;
    private long gameSeconds = 59;
    private long gameMinutes = 59;
    private int gameEnded;
    private boolean areHumansPresent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setGuiMap();
        SubmitButton.setOnAction(actionEvent -> checkWord());
        ChangeButton.setOnAction(actionEvent -> changeLetter());
        Revert.setOnAction(actionEvent -> revertMove());
        //startTime.now();
    }

    private void startGame() {
        initializeStartingValuesAndCreateGame();
        preparePlayerLetters();
        prepareGame();
        updateBoard(this.game.getBoard());
        initializePlayerPointsLabelsAndPlayerTurnLabels();
        setCurrentPlayer(this.game.getPLayer().getId());
        if (!this.game.getPLayer().getIsItHuman()) {
            playIfAI();
        }
        if (areHumansPresent) {
            startTime.now();
            newTurnClock();
            newGameClock();
        }
    }

    public void setCurrentLetter(Button letterButton) {
        if (alreadyUsedLetters.contains(letterButton)) {
            new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.WRONG_MOVE, AlertHandler.WRONG_MOVE, AlertHandler.ALREADY_USED_LETTER);
        } else {
            currentButton.setStyle("-fx-font-size: 25;" +
                    "-fx-font-weight: bold;");
            currentButton = letterButton;
            currentButton.setStyle("-fx-border-color: green;" +
                    "-fx-border-width: 4; " +
                    "-fx-font-size: 25;" +
                    "-fx-font-weight: bold;");
        }
    }

    public void updateBoard(Board board) {
        this.board = board;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                this.guiMap.get(i).get(j).setText(board.getBoard()[i][j].getLetter().toUpperCase(Locale.ROOT));
                tempBoard.board[i][j] = new Field(board.getBoard()[i][j].getLetter(), board.getBoard()[i][j].getBonus(), board.getBoard()[i][j].getCordx(), board.getBoard()[i][j].getCordy(), board.getBoard()[i][j].isEmpty());
            }
        }
    }

    public void updateTempBoard(GuiMove move) {
        this.tempBoard.board[move.getxCord()][move.getyCord()].setLetter(move.getLetter());
    }

    public void setTimes(long gameMinutes, long gameSeconds, long turnMinutes, long turnSeconds) {
        this.gameMinutes = gameMinutes;
        this.gameSeconds = gameSeconds;
        this.turnSeconds = turnSeconds;
        this.turnMinutes = turnMinutes;
    }

    private void setLetterOnMap(Button button, int i, int j) {
        if (currentButton.getText() != "") {
            if (LetterPlacingChecker.isMovePossible(tempBoard, i, j)) {
                GuiMove move = new GuiMove(i, j, currentButton.getText());
                button.setText(currentButton.getText());
                movesList.add(move);
                alreadyUsedLetters.add(currentButton);
                currentButton.setDisable(true);
                currentButton.setStyle("-fx-font-size: 25;" + "-fx-font-weight: bold;");
                currentButton = defaultButton;
                updateTempBoard(move);
            } else {
                new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.WRONG_MOVE, AlertHandler.WRONG_MOVE, AlertHandler.CANT_SET_THIS_LETTER_HERE);
            }
        } else {
            new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.WRONG_MOVE, AlertHandler.WRONG_MOVE, AlertHandler.CHOOSE_LETTER_BEFORE_MOVE);
        }
    }

    private void checkWord() {
        boolean inlineOXAxis = false;
        int wordBegin = 0;
        int wordEnd = 0;
        String word = "";
        if (LetterPlacingChecker.checkWord(this.board, movesList)) {
            if (movesList.size() > 1) {
                if (movesList.get(0).getxCord() == movesList.get(1).getxCord()) {
                    inlineOXAxis = true;
                }
            }
            if (movesList.size() == 1) {
                GuiMove field = LetterPlacingChecker.getOneNeighbour(board, movesList.get(0).getxCord(), movesList.get(0).getyCord());
                if (field.getxCord() == movesList.get(0).getxCord()) {
                    inlineOXAxis = true;
                } else {
                    inlineOXAxis = false;
                }
            }
            wordBegin = LetterPlacingChecker.findBegining(this.board, movesList, inlineOXAxis);
            wordEnd = LetterPlacingChecker.findEnding(this.board, movesList, inlineOXAxis);
            if (inlineOXAxis) {
                Field fieldStart = new Field(" ", 0, movesList.get(0).getxCord(), wordBegin, false);
                Field fieldEnd = new Field(" ", 0, movesList.get(0).getxCord(), wordEnd, false);
                word = LetterPlacingChecker.getWord(wordBegin, wordEnd, inlineOXAxis, tempBoard, movesList.get(0).getxCord());
                PossibleWords possibleWords = new PossibleWords(fieldStart, fieldEnd, word);
                System.out.println(possibleWords.getStartField().getCordx() + " start " + possibleWords.getStartField().getCordy());
                System.out.println(possibleWords.getEndField().getCordx() + " koniec " + possibleWords.getEndField().getCordy());
                System.out.println(possibleWords.getWord());
                possibleWords = this.game.play(possibleWords);
                if (!checkMove(possibleWords)) {
                    PlayerTurn logTurn = new PlayerTurn(this.game.getPLayer().getPlayerName(), true);
                    logTurn.setPoints(this.game.getPLayer().getPoints());
                    movesLog.add(logTurn);
                    clocksStop();
                    new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.WRONG_MOVE, AlertHandler.WRONG_MOVE, AlertHandler.INCORRECT_WORD);
                    revertMove();
                    changePlayer();
                    initializeNextPlayer();
                } else {
                    ArrayList<String> availableLetters = new ArrayList<>(this.game.getPLayer().getAvaibleLetters());
                    PlayerTurn logTurn = new PlayerTurn(this.game.getPLayer().getPlayerName(), false, getClonesOfMovesList(movesList), fieldStart.getCordx(), fieldStart.getCordy(), fieldEnd.getCordx(), fieldEnd.getCordy(), availableLetters);
                    logTurn.setPoints(this.game.getPLayer().getPoints());
                    movesLog.add(logTurn);
                    changePlayer();
                    initializeNextPlayer();
                }
            } else {
                Field fieldStart = new Field("x", 0, wordBegin, movesList.get(0).getyCord(), false);
                Field fieldEnd = new Field("x", 0, wordEnd, movesList.get(0).getyCord(), false);
                word = LetterPlacingChecker.getWord(wordBegin, wordEnd, inlineOXAxis, tempBoard, movesList.get(0).getyCord());
                PossibleWords possibleWords = new PossibleWords(fieldStart, fieldEnd, word);
                System.out.println(possibleWords.getStartField().getCordx() + " start " + possibleWords.getStartField().getCordy());
                System.out.println(possibleWords.getEndField().getCordx() + " koniec " + possibleWords.getEndField().getCordy());
                System.out.println(possibleWords.getWord());
                possibleWords = this.game.play(possibleWords);
                if (!checkMove(possibleWords)) {
                    PlayerTurn logTurn = new PlayerTurn(this.game.getPLayer().getPlayerName(), true);
                    logTurn.setPoints(this.game.getPLayer().getPoints());
                    movesLog.add(logTurn);
                    clocksStop();
                    new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.WRONG_MOVE, AlertHandler.WRONG_MOVE, AlertHandler.INCORRECT_WORD);
                    revertMove();
                    changePlayer();
                    initializeNextPlayer();
                } else {
                    ArrayList<String> availableLetters = new ArrayList<>(this.game.getPLayer().getAvaibleLetters());
                    PlayerTurn logTurn = new PlayerTurn(this.game.getPLayer().getPlayerName(), false, getClonesOfMovesList(movesList), fieldStart.getCordx(), fieldStart.getCordy(), fieldEnd.getCordx(), fieldEnd.getCordy(), availableLetters);
                    logTurn.setPoints(this.game.getPLayer().getPoints());
                    movesLog.add(logTurn);
                    changePlayer();
                    initializeNextPlayer();
                }

            }
        } else {
            new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.WRONG_MOVE, AlertHandler.WRONG_MOVE, AlertHandler.INCORRECT_WORD);
            revertMove();
        }
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
        areHumansPresent = players.stream().anyMatch(Player::isItHuman);
        startGame();
    }

    public static ArrayList<GuiMove> getClonesOfMovesList(ArrayList<GuiMove> movesList) {
        ArrayList<GuiMove> cloneMoves = new ArrayList<>();
        for (int i = 0; i < movesList.size(); i++) {
            cloneMoves.add(movesList.get(i));
        }
        return cloneMoves;
    }

    private void initializeStartingValuesAndCreateGame() {
        this.game = new Game(players);
        currentPlayer = 0;
        defaultButton.setText("");
        defaultButton.setId("Default");
        this.board = new Board();
        tempBoard = new Board();
        gameEnded = 0;
    }

    private void changePlayer() {
        if (this.game.endOfTheGame() && gameEnded == 0) {
            endGame();
        } else {
            setPointsLabel();
            this.game.changePlayer();
        }
    }

    private void endGame() {
        new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.WRONG_MOVE, AlertHandler.END_OF_THE_GAME, "");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/endScene.fxml"));
        ControllersHelper.changeScene(Letter1, fxmlLoader);
        endSceneController endSceneController = fxmlLoader.getController();
        endSceneController.setComponents(players, movesLog);
        try {
            clockTurn.stop();
            clockGame.stop();
        } catch (Exception e) {
            System.out.println(e);
        }
        gameEnded = 100;
    }

    private void initializeNextPlayer() {
        resetTurnTime();
        movesList.clear();
        alreadyUsedLetters.clear();
        currentButton = defaultButton;
        restoreLetters();
        enableMap();
        setPlayerLetters(this.game.getPLayer().getAvaibleLetters());
        setCurrentPlayer(this.game.getPLayer().getId());
        if (!this.game.getPLayer().getIsItHuman()) {
            playAI();
        } else {
            newTurnClock();
        }
    }

    private void playAI() {
        if (gameEnded == 0) {
            cleanLetters();
            disableMap();
            PossibleWords temp = game.play(new PossibleWords(new Field("", 0, 7, 7, false), new Field("", 0, 9, 7, false), "sen"));
            if (!temp.getWord().equals("0")) {
                addMoveToMovesLog(temp);
            } else {
                PlayerTurn moveLog = new PlayerTurn(this.game.getPLayer().getPlayerName(), true);
                moveLog.setPoints(this.game.getPLayer().getPoints());
                movesLog.add(moveLog);
            }
            updateBoard(this.game.getBoard());
            new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.WRONG_MOVE, AlertHandler.END_OF_AI_MOVE, "");
            changePlayer();
            if (!this.game.endOfTheGame()) {
                initializeNextPlayer();
            }
        }
    }

    private void cleanLetters() {
        for (Button button : playerLetter) {
            button.setText(" ");
        }
    }

    private void addMoveToMovesLog(PossibleWords word) {
        Field startField = word.getStartField();
        Field endField = word.getEndField();
        Boolean inLineWithOX = false;
        ArrayList<GuiMove> movesLogList = new ArrayList<>();
        if (startField.getCordx() == endField.getCordx()) {
            inLineWithOX = true;
        }
        if (inLineWithOX) {
            for (int i = 0; i < word.getWord().length(); i++) {
                GuiMove move = new GuiMove(startField.getCordx(), startField.getCordy() + i, this.board.getBoard()[startField.getCordx()][startField.getCordy() + i].getLetter().toUpperCase(Locale.ROOT));
                movesLogList.add(move);
            }
        } else {
            for (int i = 0; i < word.getWord().length(); i++) {
                GuiMove move = new GuiMove(startField.getCordx() + i, startField.getCordy(), this.board.getBoard()[startField.getCordx() + i][startField.getCordy()].getLetter().toUpperCase(Locale.ROOT));
                movesLogList.add(move);
            }
        }
        ArrayList<String> availableLetters = new ArrayList<>(this.game.getPLayer().getAvaibleLetters());
        PlayerTurn movesLog = new PlayerTurn(this.game.getPLayer().getPlayerName(), false, movesLogList, word.getStartField().getCordx(), word.getStartField().getCordy(), word.getEndField().getCordx(), word.getEndField().getCordy(), availableLetters);
        movesLog.setPoints(this.game.getPLayer().getPoints());
        this.movesLog.add(movesLog);
    }

    private void disableMap() {
        for (Button button : playerLetter) {
            button.setDisable(true);
        }
        SubmitButton.setDisable(true);
        ChangeButton.setDisable(true);
    }

    private void enableMap() {
        for (Button button : playerLetter) {
            button.setDisable(false);
        }
        SubmitButton.setDisable(false);
        ChangeButton.setDisable(false);
    }

    private void playIfAI() {
        playAI();
    }

    private void preparePlayerLetters() {
        playerLetter.add(Letter1);
        playerLetter.add(Letter2);
        playerLetter.add(Letter3);
        playerLetter.add(Letter4);
        playerLetter.add(Letter5);
        playerLetter.add(Letter6);
        playerLetter.add(Letter7);
        initializePlayerLetters();
    }

    private void initializeGuiMap() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                final int xi = i;
                final int xj = j;
                Button currentButtonMap = guiMap.get(i).get(j);
                guiMap.get(i).get(j).setDisable(false);
                currentButtonMap.setDisable(false);
                guiMap.get(i).get(j).setOnAction(actionEvent -> setLetterOnMap(currentButtonMap, xi, xj));
                guiMap.get(i).get(j).setStyle(guiMap.get(i).get(j).getStyle() + "-fx-font-weight: bold;" + "-fx-font-size: 15;");
            }
        }
    }

    private void initializePlayerLetters() {
        for (int i = 0; i < 7; i++) {
            Button currentButtonLetter = playerLetter.get(i);
            currentButtonLetter.setDisable(false);
            playerLetter.get(i).setOnAction(actionEvent -> setCurrentLetter(currentButtonLetter));
            playerLetter.get(i).setStyle("-fx-font-weight: bold;" +
                    "-fx-font-size: 25; ");
        }
    }

    private boolean checkMove(PossibleWords returnedValue) {
        if (returnedValue.getWord() == "0") {
            return false;
        }
        return true;
    }

    private void revertMove() {
        this.movesList = new ArrayList<>();
        updateBoard(game.getBoard());
        restoreLetters();
        alreadyUsedLetters.clear();
    }

    private void restoreLetters() {
        for (int i = 0; i < playerLetter.size(); i++) {
            playerLetter.get(i).setDisable(false);
            playerLetter.get(i).setStyle("-fx-font-weight: bold;" +
                    "-fx-font-size: 25; ");
        }
    }

    private void setCurrentPlayer(int playerIndex) {
        for (Label lbl : playerTurn) {
            lbl.setStyle(labelStyle);
        }
        playerTurn.get(playerIndex).setStyle(currentLabelStyle);
    }

    private void prepareGame() {
        setPlayerLetters(this.game.getPLayer().getAvaibleLetters());
    }

    private void setPlayerLetters(ArrayList<String> letters) {
        for (int i = 0; i < this.playerLetter.size(); i++) {
            playerLetter.get(i).setText("");
        }
        for (int i = 0; i < letters.size(); i++) {
            playerLetter.get(i).setText(letters.get(i).toUpperCase(Locale.ROOT));
        }
    }

    private void changeLetter() {
        if (currentButton == defaultButton) {
            new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.WRONG_MOVE, AlertHandler.WRONG_MOVE, AlertHandler.EMPTY_LETTER);
        } else {
            PlayerTurn logTurn = new PlayerTurn(this.game.getPLayer().getPlayerName(), true);
            logTurn.setPoints(this.game.getPLayer().getPoints());
            movesLog.add(logTurn);
            revertMove();
            game.changeLetter(game.getPLayer().getAvaibleLetters().indexOf(currentButton.getText().toLowerCase()));
            changePlayer();
            initializeNextPlayer();
        }
    }

    private void setPointsLabel() {
        int maxPlayerIndex = 0;
        int maxPlayerValue = 0;
        clearPointsLabel();
        for (int i = 0; i < players.size(); i++) {
            if (this.game.getPLayerByIndex(i).getPoints() > maxPlayerValue) {
                maxPlayerIndex = this.game.getPLayerByIndex(i).getId();
                maxPlayerValue = this.game.getPLayerByIndex(i).getPoints();
            }
            playerPoints2.get(i).setText("" + game.getPLayerByIndex(i).getPoints());
        }
        playerPoints1.get(maxPlayerIndex).setStyle(currentLabelStyle);
        playerPoints2.get(maxPlayerIndex).setStyle(currentLabelStyle);
        playerPoints2.get(maxPlayerIndex).setText("" + maxPlayerValue);
    }

    private void clearPointsLabel() {
        for (int j = 0; j < playerPoints1.size(); j++) {
            playerPoints1.get(j).setStyle(labelStyle);
            playerPoints2.get(j).setStyle(labelStyle);
        }
    }

    private void initializePlayerPointsLabelsAndPlayerTurnLabels() {
        playerTurn.add(TurnP1);
        playerTurn.add(TurnP2);
        playerTurn.add(TurnP3);
        playerTurn.add(TurnP4);
        playerPoints1.add(P1Label);
        playerPoints1.add(P2Label);
        playerPoints1.add(P3Label);
        playerPoints1.add(P4Label);
        playerPoints2.add(PointsP1);
        playerPoints2.add(PointsP2);
        playerPoints2.add(PointsP3);
        playerPoints2.add(PointsP4);
        for (int i = 0; i < 4; i++) {
            playerTurn.get(i).setText("");
            playerPoints1.get(i).setText("");
            playerPoints2.get(i).setText("");
        }
        for (int i = 0; i < players.size(); i++) {
            playerTurn.get(i).setText("Gracz " + (i + 1));
            playerPoints1.get(i).setText("Gracz " + (i + 1));
            playerPoints2.get(i).setText("0");
        }
    }

    private void newTurnClock() {
        if (clockTurn != null) {
            clockTurn.stop();
        }
        clockTurn = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            if ((startTime.getSecond() >= this.turnSeconds) && (startTime.getMinute() >= this.turnMinutes)) {
                clockTurn.stop();
                PlayerTurn logTurn = new PlayerTurn(this.game.getPLayer().getPlayerName(), true);
                logTurn.setPoints(this.game.getPLayer().getPoints());
                movesLog.add(logTurn);
                revertMove();
                this.game.getPLayer().setTurnsMissingPlay(this.game.getPLayer().getTurnsMissingPlay() + 1);
                changePlayer();
                initializeNextPlayer();
            } else {
                if (startTime.getNano() != 0) {
                    turnTimeLbl.setText(startTime.getMinute() + ":" + startTime.getSecond() + ":" + (startTime.getNano() / 100000000));
                } else {
                    turnTimeLbl.setText(startTime.getMinute() + ":" + startTime.getSecond() + ":0");
                }
            }
            startTime = startTime.plusNanos(100000000);
        }), new KeyFrame(Duration.seconds(0.1)));
        clockTurn.setCycleCount(Timeline.INDEFINITE);
        clockTurn.play();
    }

    private void newGameClock() {
        if (clockGame != null) {
            clockGame.stop();
        }
        clockGame = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            if ((startTimeGlobal.getMinute() >= this.gameMinutes) && (startTimeGlobal.getSecond() >= this.gameSeconds)) {
                clockGame.stop();
                endGame();
            } else {
                if (startTimeGlobal.getNano() != 0) {
                    turnTimeLbl1.setText(startTimeGlobal.getMinute() + ":" + startTimeGlobal.getSecond() + ":" + (startTimeGlobal.getNano() / 100000000));
                } else {
                    turnTimeLbl1.setText(startTimeGlobal.getMinute() + ":" + startTimeGlobal.getSecond() + ":0");
                }
            }
            startTimeGlobal = startTimeGlobal.plusNanos(100000000);
        }), new KeyFrame(Duration.seconds(0.1)));
        clockGame.setCycleCount(Timeline.INDEFINITE);
        if (gameEnded == 0) {
            clockGame.play();
        }
    }

    private void resetTurnTime() {
        startTime = LocalTime.of(0, 0, 0);
    }

    private void clocksStop() {
        clockTurn.pause();
    }

    private void clocksResume() {
        clockTurn.play();
    }

    private void setGuiMap() {
        for (int i = 0; i < 15; i++) {
            guiMap.add(new ArrayList<Button>());
        }
        guiMap.get(0).add(p0_0);
        guiMap.get(0).add(p0_1);
        guiMap.get(0).add(p0_2);
        guiMap.get(0).add(p0_3);
        guiMap.get(0).add(p0_4);
        guiMap.get(0).add(p0_5);
        guiMap.get(0).add(p0_6);
        guiMap.get(0).add(p0_7);
        guiMap.get(0).add(p0_8);
        guiMap.get(0).add(p0_9);
        guiMap.get(0).add(p0_10);
        guiMap.get(0).add(p0_11);
        guiMap.get(0).add(p0_12);
        guiMap.get(0).add(p0_13);
        guiMap.get(0).add(p0_14);

        guiMap.get(1).add(p1_0);
        guiMap.get(1).add(p1_1);
        guiMap.get(1).add(p1_2);
        guiMap.get(1).add(p1_3);
        guiMap.get(1).add(p1_4);
        guiMap.get(1).add(p1_5);
        guiMap.get(1).add(p1_6);
        guiMap.get(1).add(p1_7);
        guiMap.get(1).add(p1_8);
        guiMap.get(1).add(p1_9);
        guiMap.get(1).add(p1_10);
        guiMap.get(1).add(p1_11);
        guiMap.get(1).add(p1_12);
        guiMap.get(1).add(p1_13);
        guiMap.get(1).add(p1_14);

        guiMap.get(2).add(p2_0);
        guiMap.get(2).add(p2_1);
        guiMap.get(2).add(p2_2);
        guiMap.get(2).add(p2_3);
        guiMap.get(2).add(p2_4);
        guiMap.get(2).add(p2_5);
        guiMap.get(2).add(p2_6);
        guiMap.get(2).add(p2_7);
        guiMap.get(2).add(p2_8);
        guiMap.get(2).add(p2_9);
        guiMap.get(2).add(p2_10);
        guiMap.get(2).add(p2_11);
        guiMap.get(2).add(p2_12);
        guiMap.get(2).add(p2_13);
        guiMap.get(2).add(p2_14);

        guiMap.get(3).add(p3_0);
        guiMap.get(3).add(p3_1);
        guiMap.get(3).add(p3_2);
        guiMap.get(3).add(p3_3);
        guiMap.get(3).add(p3_4);
        guiMap.get(3).add(p3_5);
        guiMap.get(3).add(p3_6);
        guiMap.get(3).add(p3_7);
        guiMap.get(3).add(p3_8);
        guiMap.get(3).add(p3_9);
        guiMap.get(3).add(p3_10);
        guiMap.get(3).add(p3_11);
        guiMap.get(3).add(p3_12);
        guiMap.get(3).add(p3_13);
        guiMap.get(3).add(p3_14);

        guiMap.get(4).add(p4_0);
        guiMap.get(4).add(p4_1);
        guiMap.get(4).add(p4_2);
        guiMap.get(4).add(p4_3);
        guiMap.get(4).add(p4_4);
        guiMap.get(4).add(p4_5);
        guiMap.get(4).add(p4_6);
        guiMap.get(4).add(p4_7);
        guiMap.get(4).add(p4_8);
        guiMap.get(4).add(p4_9);
        guiMap.get(4).add(p4_10);
        guiMap.get(4).add(p4_11);
        guiMap.get(4).add(p4_12);
        guiMap.get(4).add(p4_13);
        guiMap.get(4).add(p4_14);

        guiMap.get(5).add(p5_0);
        guiMap.get(5).add(p5_1);
        guiMap.get(5).add(p5_2);
        guiMap.get(5).add(p5_3);
        guiMap.get(5).add(p5_4);
        guiMap.get(5).add(p5_5);
        guiMap.get(5).add(p5_6);
        guiMap.get(5).add(p5_7);
        guiMap.get(5).add(p5_8);
        guiMap.get(5).add(p5_9);
        guiMap.get(5).add(p5_10);
        guiMap.get(5).add(p5_11);
        guiMap.get(5).add(p5_12);
        guiMap.get(5).add(p5_13);
        guiMap.get(5).add(p5_14);

        guiMap.get(6).add(p6_0);
        guiMap.get(6).add(p6_1);
        guiMap.get(6).add(p6_2);
        guiMap.get(6).add(p6_3);
        guiMap.get(6).add(p6_4);
        guiMap.get(6).add(p6_5);
        guiMap.get(6).add(p6_6);
        guiMap.get(6).add(p6_7);
        guiMap.get(6).add(p6_8);
        guiMap.get(6).add(p6_9);
        guiMap.get(6).add(p6_10);
        guiMap.get(6).add(p6_11);
        guiMap.get(6).add(p6_12);
        guiMap.get(6).add(p6_13);
        guiMap.get(6).add(p6_14);

        guiMap.get(7).add(p7_0);
        guiMap.get(7).add(p7_1);
        guiMap.get(7).add(p7_2);
        guiMap.get(7).add(p7_3);
        guiMap.get(7).add(p7_4);
        guiMap.get(7).add(p7_5);
        guiMap.get(7).add(p7_6);
        guiMap.get(7).add(p7_7);
        guiMap.get(7).add(p7_8);
        guiMap.get(7).add(p7_9);
        guiMap.get(7).add(p7_10);
        guiMap.get(7).add(p7_11);
        guiMap.get(7).add(p7_12);
        guiMap.get(7).add(p7_13);
        guiMap.get(7).add(p7_14);

        guiMap.get(8).add(p8_0);
        guiMap.get(8).add(p8_1);
        guiMap.get(8).add(p8_2);
        guiMap.get(8).add(p8_3);
        guiMap.get(8).add(p8_4);
        guiMap.get(8).add(p8_5);
        guiMap.get(8).add(p8_6);
        guiMap.get(8).add(p8_7);
        guiMap.get(8).add(p8_8);
        guiMap.get(8).add(p8_9);
        guiMap.get(8).add(p8_10);
        guiMap.get(8).add(p8_11);
        guiMap.get(8).add(p8_12);
        guiMap.get(8).add(p8_13);
        guiMap.get(8).add(p8_14);

        guiMap.get(9).add(p9_0);
        guiMap.get(9).add(p9_1);
        guiMap.get(9).add(p9_2);
        guiMap.get(9).add(p9_3);
        guiMap.get(9).add(p9_4);
        guiMap.get(9).add(p9_5);
        guiMap.get(9).add(p9_6);
        guiMap.get(9).add(p9_7);
        guiMap.get(9).add(p9_8);
        guiMap.get(9).add(p9_9);
        guiMap.get(9).add(p9_10);
        guiMap.get(9).add(p9_11);
        guiMap.get(9).add(p9_12);
        guiMap.get(9).add(p9_13);
        guiMap.get(9).add(p9_14);

        guiMap.get(10).add(p10_0);
        guiMap.get(10).add(p10_1);
        guiMap.get(10).add(p10_2);
        guiMap.get(10).add(p10_3);
        guiMap.get(10).add(p10_4);
        guiMap.get(10).add(p10_5);
        guiMap.get(10).add(p10_6);
        guiMap.get(10).add(p10_7);
        guiMap.get(10).add(p10_8);
        guiMap.get(10).add(p10_9);
        guiMap.get(10).add(p10_10);
        guiMap.get(10).add(p10_11);
        guiMap.get(10).add(p10_12);
        guiMap.get(10).add(p10_13);
        guiMap.get(10).add(p10_14);

        guiMap.get(11).add(p11_0);
        guiMap.get(11).add(p11_1);
        guiMap.get(11).add(p11_2);
        guiMap.get(11).add(p11_3);
        guiMap.get(11).add(p11_4);
        guiMap.get(11).add(p11_5);
        guiMap.get(11).add(p11_6);
        guiMap.get(11).add(p11_7);
        guiMap.get(11).add(p11_8);
        guiMap.get(11).add(p11_9);
        guiMap.get(11).add(p11_10);
        guiMap.get(11).add(p11_11);
        guiMap.get(11).add(p11_12);
        guiMap.get(11).add(p11_13);
        guiMap.get(11).add(p11_14);

        guiMap.get(12).add(p12_0);
        guiMap.get(12).add(p12_1);
        guiMap.get(12).add(p12_2);
        guiMap.get(12).add(p12_3);
        guiMap.get(12).add(p12_4);
        guiMap.get(12).add(p12_5);
        guiMap.get(12).add(p12_6);
        guiMap.get(12).add(p12_7);
        guiMap.get(12).add(p12_8);
        guiMap.get(12).add(p12_9);
        guiMap.get(12).add(p12_10);
        guiMap.get(12).add(p12_11);
        guiMap.get(12).add(p12_12);
        guiMap.get(12).add(p12_13);
        guiMap.get(12).add(p12_14);

        guiMap.get(13).add(p13_0);
        guiMap.get(13).add(p13_1);
        guiMap.get(13).add(p13_2);
        guiMap.get(13).add(p13_3);
        guiMap.get(13).add(p13_4);
        guiMap.get(13).add(p13_5);
        guiMap.get(13).add(p13_6);
        guiMap.get(13).add(p13_7);
        guiMap.get(13).add(p13_8);
        guiMap.get(13).add(p13_9);
        guiMap.get(13).add(p13_10);
        guiMap.get(13).add(p13_11);
        guiMap.get(13).add(p13_12);
        guiMap.get(13).add(p13_13);
        guiMap.get(13).add(p13_14);

        guiMap.get(14).add(p14_0);
        guiMap.get(14).add(p14_1);
        guiMap.get(14).add(p14_2);
        guiMap.get(14).add(p14_3);
        guiMap.get(14).add(p14_4);
        guiMap.get(14).add(p14_5);
        guiMap.get(14).add(p14_6);
        guiMap.get(14).add(p14_7);
        guiMap.get(14).add(p14_8);
        guiMap.get(14).add(p14_9);
        guiMap.get(14).add(p14_10);
        guiMap.get(14).add(p14_11);
        guiMap.get(14).add(p14_12);
        guiMap.get(14).add(p14_13);
        guiMap.get(14).add(p14_14);
        initializeGuiMap();
    }
}
