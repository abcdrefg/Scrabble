package Game;

import wordFinder.LettersInBox;

import java.util.ArrayList;

public class Player {
    private boolean isItHuman;
    private ArrayList<String> AvaibleLetters = new ArrayList<>();
    private int points;
    private int turnsMissingPlay;
    private boolean eliminated;
    private String playerName;
    private String aiPlayerLevel;
    private int maxPointsPlayerCouldGet;
    private int id;

    public Player(boolean isItHuman){
        this.isItHuman = isItHuman;
        this.points = 0;
        this.turnsMissingPlay = 0;
        this.eliminated = false;
        this.maxPointsPlayerCouldGet = 0;
    }

    public void makeHand(LettersInBox lettersInBox){
        AvaibleLetters.clear();
        for (int i = 0; i < 7; i++) {
            AvaibleLetters.add(lettersInBox.getRandomLetterFromBox());
        }
    }

    public void drawLetter(LettersInBox lettersInBox){
        AvaibleLetters.add(lettersInBox.getRandomLetterFromBox());
    }

    public void changeLetter(LettersInBox lettersInBox, int index){
        lettersInBox.putLetterInBox(AvaibleLetters.get(index));
        AvaibleLetters.remove(index);
        AvaibleLetters.add(lettersInBox.getRandomLetterFromBox());
    }

    public void changeLetters(LettersInBox lettersInBox, ArrayList<String> index){
        if (index.size() == 0){
            return;
        }
        if (index.size() == 1){
            changeLetter(lettersInBox, AvaibleLetters.indexOf(index.get(0)));
            return;
        }
        for (int i = 0; i < index.size(); i++) {
            lettersInBox.putLetterInBox(index.get(i));
            AvaibleLetters.remove(index.get(i));
            AvaibleLetters.add(lettersInBox.getRandomLetterFromBox());
        }
    }

    public void removeLetterFromHand(String lettter){
        AvaibleLetters.remove(lettter);
    }

    public ArrayList<String> getAvaibleLetters() {
        return AvaibleLetters;
    }

    public boolean getIsItHuman() {
        return isItHuman;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getTurnsMissingPlay() {
        return turnsMissingPlay;
    }

    public void setTurnsMissingPlay(int turnsMissingPlay) {
        this.turnsMissingPlay = turnsMissingPlay;
    }

    public int getLevel(){
        return 0;
    }

    public void eliminate(){
        this.eliminated = true;
    }

    public boolean isEliminated() {
        return eliminated;
    }

    public boolean isItHuman() {
        return isItHuman;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getAiPlayerLevel() {
        return aiPlayerLevel;
    }

    public void setItHuman(boolean itHuman) {
        isItHuman = itHuman;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setAiPlayerLevel(String aiPlayerLevel) {
        this.aiPlayerLevel = aiPlayerLevel;
    }

    public int getMaxPointsPlayerCouldGet() {
        return maxPointsPlayerCouldGet;
    }

    public void setMaxPointsPlayerCouldGet(int maxPointsPlayerCouldGet) {
        this.maxPointsPlayerCouldGet = maxPointsPlayerCouldGet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void main(String[] args) {
        LettersInBox lettersInBox = new LettersInBox();
        Player player = new Player(true);
        player.makeHand(lettersInBox);
        System.out.println(player.getAvaibleLetters());
        ArrayList<String> list = new ArrayList<>();
        list.add(player.getAvaibleLetters().get(1));
        list.add(player.getAvaibleLetters().get(4));
        player.changeLetters(lettersInBox, list);
        System.out.println(player.getAvaibleLetters());
    }
}
