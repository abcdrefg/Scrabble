package replay;

import gui.GuiMove;

import java.util.ArrayList;
import java.util.List;

public class PlayerTurn {
    private final String playerName;
    private int points;
    private final boolean isTurnFolded;
    private static int counter = 0;
    private final int turnNumber;
    private List<GuiMove> listOfMoves;
    private int firstXCord;
    private int firstYCord;
    private int secondXCord;
    private int secondYCord;
    private ArrayList<String> letters;

    public PlayerTurn(String playerName, boolean isTurnFolded) {
        this.isTurnFolded = isTurnFolded;
        this.playerName = playerName;
        counter++;
        turnNumber = counter;
    }

    public PlayerTurn(String playerName, boolean isTurnFolded, List<GuiMove> listOfMoves, int firstXCord, int firstYCord, int secondXCord, int secondYCord, ArrayList<String> letters) {
        this.isTurnFolded = isTurnFolded;
        this.playerName = playerName;
        this.listOfMoves = listOfMoves;
        this.firstXCord = firstXCord;
        this.firstYCord = firstYCord;
        this.secondYCord = secondYCord;
        this.secondXCord = secondXCord;
        this.letters = letters;
        counter++;
        turnNumber = counter;
    }

    public ArrayList<String> getLetters() {
        return letters;
    }

    public static int getCounter() {
        return counter;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPoints() {
        return points;
    }

    public boolean isTurnFolded() {
        return isTurnFolded;
    }

    public List<GuiMove> getListOfMoves() {
        return listOfMoves;
    }

    public void setListOfMoves(List<GuiMove> listOfMoves) {
        this.listOfMoves = listOfMoves;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getFirstXCord() {
        return firstXCord;
    }

    public int getFirstYCord() {
        return firstYCord;
    }

    public int getSecondXCord() {
        return secondXCord;
    }

    public int getSecondYCord() {
        return secondYCord;
    }
}
