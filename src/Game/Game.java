package Game;

import ScrabbleBoard.Board;
import ScrabbleBoard.Field;
import aiPlayer.AIPlayer;
import fieldFinder.HorizontalFieldsFinder;
import fieldFinder.VerticalFieldsFinder;
import fieldFinder.Word;
import javafx.geometry.Pos;
import wordFinder.*;
import Game.*;
import Game.Player;

import java.util.*;

public class Game {
    /*private Player Player1;
    private Player Player2;
    private Player Player3;
    private Player Player4;*/
    private List<Player> listOfPlayers;
    private int currentPlayer;
    private Board board = new Board();
    private int turn;
    private List<List<Word>> list = new ArrayList<>(new ArrayList<>());
    private HashMap<Integer, PossibleWords> listWithAllWordsWithPoints = new HashMap<Integer, PossibleWords>();
    private LettersInBox lettersInBox = new LettersInBox();
    private LetterPoints letterPoints = new LetterPoints();


    public Game(List<Player> listOfPlayers){
        /*this.Player1 = Player1;
        this.Player2 = Player2;
        this.Player3 = Player3;
        this.Player4 = Player4;*/
        this.listOfPlayers = listOfPlayers;
        this.currentPlayer = 0;
        for(int i = 0; i < listOfPlayers.size(); i++){
            this.listOfPlayers.get(i).makeHand(lettersInBox);
            this.listOfPlayers.get(i).setId(i);
        }

    }

    public PossibleWords play(PossibleWords whatHumanPlayed){
        //choosing player
        Player player = getPLayer();
        /*switch (playerNumer) {
            case 1:
                player = this.Player1;
                break;
            case 2:
                player = this.Player2;
                break;
            case 3:
                player = this.Player3;
                break;
            case 4:
                player = this.Player4;
                break;
            default:
                player = this.Player1;
        }*/

        PossibleWords choosenWord;
        //how many letters we need to draw to get 7
        /*for (int i = player.getAvaibleLetters().size(); i < 7; i++){
            if(isThereAnyToDraw() == 0){
                break;
            }
            player.drawLetter(lettersInBox);
        }*/
        System.out.println(currentPlayer);
        System.out.println(player.getAvaibleLetters());
        System.out.println(player.getPoints());

        //play depends if human or ai
        if (player.getIsItHuman()){
            /*Scanner reader = new Scanner(System.in);
            System.out.println("Start X:");
            int startX = reader.nextInt();
            System.out.println("Start Y:");
            int startY = reader.nextInt();
            System.out.println("End X:");
            int endX = reader.nextInt();
            System.out.println("End Y:");
            int endY = reader.nextInt();
            System.out.println("Word:");
            String word = reader.next();*/
            choosenWord = playHuman(player, whatHumanPlayed);
            //choosenWord = playHuman(player, new PossibleWords(new Field("", 0, startX, startY, false), new Field("", 0, endX, endY, false), word));
        }else{
            choosenWord = playAi(player);
        }

        //change current player
        //changePlayer();
        //return what ai change or if human change was acceptable
        //how many letters we need to draw to get 7
        for (int i = player.getAvaibleLetters().size(); i < 7; i++){
            if(isThereAnyToDraw() == 0){
                break;
            }
            player.drawLetter(lettersInBox);
        }
        return choosenWord;
    }

    public PossibleWords playHuman(Player player, PossibleWords choosenWord){
        //we check board how its now so we can go back to it

        int pointsCouldGet = maxPointsHumanCouldGet(player);
        String word = "";
        PossibleWords copyOfBoard;
        choosenWord.setWord(choosenWord.getWord().toLowerCase(Locale.ROOT));
        //poziome
        if (choosenWord.getStartField().getCordx() == choosenWord.getEndField().getCordx()){
            for(int i = choosenWord.getStartField().getCordy(); i <= choosenWord.getEndField().getCordy(); i++){
                word = word + board.getBoard()[choosenWord.getStartField().getCordx()][i].getLetter();
            }
            copyOfBoard = new PossibleWords(choosenWord.getStartField(), choosenWord.getEndField(), word);
        }else {
            for(int i = choosenWord.getStartField().getCordx(); i <= choosenWord.getEndField().getCordx(); i++){
                word = word + board.getBoard()[i][choosenWord.getStartField().getCordy()].getLetter();
            }
            copyOfBoard = new PossibleWords(choosenWord.getStartField(), choosenWord.getEndField(), word);
        }

        //points if we add them
        int points = howManyPoints(choosenWord);

        //we change board as human make move
        board.changeLettersInBoard(choosenWord);
        //we check if every word on board is in dictionary if not we reverse change human did
        HorizontalFieldsFinder horizontalFieldsFinder = new HorizontalFieldsFinder(board);
        VerticalFieldsFinder verticalFieldsFinder = new VerticalFieldsFinder(board);
        List<Word> listOfHorizontalWords = new ArrayList<>();
        List<Word> listOfVerticalWords = new ArrayList<>();
        listOfHorizontalWords = horizontalFieldsFinder.findAllHorizontalWords();
        listOfVerticalWords = verticalFieldsFinder.findAllVerticalWords();
        if(turn == 0 && choosenWord.getWord().length() == 1) {
            player.setTurnsMissingPlay(player.getTurnsMissingPlay() + 1);
            player.setMaxPointsPlayerCouldGet(player.getMaxPointsPlayerCouldGet() + pointsCouldGet);
            if (player.getTurnsMissingPlay() == 3){
                player.eliminate();
            }
            board.changeLettersInBoard(copyOfBoard);
            return new PossibleWords(board.getBoard()[7][7], board.getBoard()[7][7], "0");
        }
        if(isItAllWorlds(listOfHorizontalWords) && isItAllWorlds(listOfVerticalWords)){
            //usuwamy z reki
            for (int i = 0; i < choosenWord.getWord().length(); i++){
                if(!word.contains(Character.toString(choosenWord.getWord().charAt(i)))){
                    player.removeLetterFromHand(Character.toString(choosenWord.getWord().charAt(i)));
                }
            }
            turn = turn + 1;
            player.setPoints(player.getPoints() + points);
            player.setTurnsMissingPlay(0);
            player.setMaxPointsPlayerCouldGet(player.getMaxPointsPlayerCouldGet() + pointsCouldGet);
            return choosenWord;
        }else{
            player.setTurnsMissingPlay(player.getTurnsMissingPlay() + 1);
            player.setMaxPointsPlayerCouldGet(player.getMaxPointsPlayerCouldGet() + pointsCouldGet);
            if (player.getTurnsMissingPlay() == 3){
                player.eliminate();
            }
            board.changeLettersInBoard(copyOfBoard);
            return new PossibleWords(board.getBoard()[7][7], board.getBoard()[7][7], "0");
        }
    }


    public PossibleWords playAi(Player player){
        PossibleWords choosenWord = new PossibleWords(board.getBoard()[7][7], board.getBoard()[7][7], "0");
        int points = 0;
        if(turn == 0) {
            TrueWordFinder trueWordFinder = new TrueWordFinder(list);
            trueWordFinder.FindWordForEmptyBoard(player.getAvaibleLetters(), board);
            listWithAllWordsWithPoints = trueWordFinder.getListWithAllWordsWithPoints();

            if(listWithAllWordsWithPoints.size() == 0){
                changeLetter(0);
                if (player.getTurnsMissingPlay() == 3){
                    player.eliminate();
                }
                return choosenWord;
            }

            ArrayList<Integer> listWithPointsToSelect = new ArrayList<>();
            for (int i = 0 ; i < 100; i++){
                if (listWithAllWordsWithPoints.containsKey(i)){
                    //choosenWord = listWithAllWordsWithPoints.get(i);
                    listWithPointsToSelect.add(i);
                }
            }
            Collections.sort(listWithPointsToSelect);
            //easy 50%
            if(player.getAiPlayerLevel().equals("Łatwy")){
                choosenWord = listWithAllWordsWithPoints.get(listWithPointsToSelect.get((listWithPointsToSelect.size() - 1) / 2));
                points = listWithPointsToSelect.get(listWithPointsToSelect.size() / 2);
            }
            //medium 75%
            if(player.getAiPlayerLevel().equals("Średni")){
                choosenWord = listWithAllWordsWithPoints.get(listWithPointsToSelect.get((listWithPointsToSelect.size() - 1) * 3 / 4));
                points = listWithPointsToSelect.get(listWithPointsToSelect.size() * 3 / 4);
            }
            //hard 100%
            if(player.getAiPlayerLevel().equals("Trudny")){
                choosenWord = listWithAllWordsWithPoints.get(listWithPointsToSelect.get(listWithPointsToSelect.size() - 1));
                points = listWithPointsToSelect.get(listWithPointsToSelect.size() - 1);
            }
        }else{
            HorizontalFieldsFinder horizontalFieldsFinder = new HorizontalFieldsFinder(board);
            VerticalFieldsFinder verticalFieldsFinder = new VerticalFieldsFinder(board);
            double start = System.currentTimeMillis();
            List<List<Word>> listOfPossibleWords1 = verticalFieldsFinder.findAllVerticalPossibleWords();
            System.out.println(System.currentTimeMillis() - start);
            start = System.currentTimeMillis();
            List<List<Word>> listOfPossibleWords2 = horizontalFieldsFinder.findAllHorizontalPossibleWords();
            System.out.println(System.currentTimeMillis() - start);
            TrueWordFinder trueWordFinder = new TrueWordFinder(listOfPossibleWords1);
            trueWordFinder.FindAllWorlds(player.getAvaibleLetters(), board);
            trueWordFinder.setListWithAllPossibleFields(listOfPossibleWords2);
            trueWordFinder.FindAllWorlds(player.getAvaibleLetters(), board);
            listWithAllWordsWithPoints = trueWordFinder.getListWithAllWordsWithPoints();

            if(listWithAllWordsWithPoints.size() == 0){
                changeLetter(0);
                if (player.getTurnsMissingPlay() == 3){
                    player.eliminate();
                }
                return choosenWord;
            }

            ArrayList<Integer> listWithPointsToSelect = new ArrayList<>();
            for (int i = 0 ; i < 100; i++){
                if (listWithAllWordsWithPoints.containsKey(i)){
                    //choosenWord = listWithAllWordsWithPoints.get(i);
                    listWithPointsToSelect.add(i);
                }
            }
            Collections.sort(listWithPointsToSelect);
            //easy 50%
            if(player.getAiPlayerLevel().equals("Łatwy")){
                choosenWord = listWithAllWordsWithPoints.get(listWithPointsToSelect.get((listWithPointsToSelect.size() - 1) / 2));
                points = listWithPointsToSelect.get(listWithPointsToSelect.size() / 2);
            }
            //medium 75%
            if(player.getAiPlayerLevel().equals("Średni")){
                choosenWord = listWithAllWordsWithPoints.get(listWithPointsToSelect.get((listWithPointsToSelect.size() - 1)* 3 / 4));
                points = listWithPointsToSelect.get(listWithPointsToSelect.size() * 3 / 4);
            }
            //hard 100%
            if(player.getAiPlayerLevel().equals("Trudny")){
                choosenWord = listWithAllWordsWithPoints.get(listWithPointsToSelect.get(listWithPointsToSelect.size() - 1));
                points = listWithPointsToSelect.get(listWithPointsToSelect.size() - 1);
            }
        }

        String word = "";
        //poziome
        if (choosenWord.getStartField().getCordx() == choosenWord.getEndField().getCordx()){
            for(int i = choosenWord.getStartField().getCordy(); i <= choosenWord.getEndField().getCordy(); i++){
                word = word + board.getBoard()[choosenWord.getStartField().getCordx()][i].getLetter();
            }
        }else {
            for(int i = choosenWord.getStartField().getCordx(); i <= choosenWord.getEndField().getCordx(); i++){
                word = word + board.getBoard()[i][choosenWord.getStartField().getCordy()].getLetter();
            }
        }

        //usuwamy z reki
        for (int i = 0; i < choosenWord.getWord().length(); i++){
            if(!word.contains(Character.toString(choosenWord.getWord().charAt(i)))){
                player.removeLetterFromHand(Character.toString(choosenWord.getWord().charAt(i)));
            }
        }

        //punkty
        player.setPoints(player.getPoints() + points);

        //if we got word we change board
        if (!choosenWord.getWord().equals("0")) {
            player.setTurnsMissingPlay(0);
            board.changeLettersInBoard(choosenWord);
            turn = turn + 1;
        }else {
            player.setTurnsMissingPlay(player.getTurnsMissingPlay() + 1);
            changeLetter(0);
            if (player.getTurnsMissingPlay() == 3){
                player.eliminate();
            }
        }

        return choosenWord;
    }

    public boolean isItAllWorlds(List<Word> listWithWorlds){
        ArrayList<String> dictionary = letterPoints.getDictionary();
        String word = "";
        for(int i = 0; i < listWithWorlds.size(); i++){
            List<Field> listOfFields = new ArrayList<>();
            listOfFields = listWithWorlds.get(i).getListOfFields();
            if (listOfFields.size() == 1){
                continue;
            }
            word = "";
            for(int j = 0; j < listOfFields.size(); j++){
                word = word + board.getBoard()[listOfFields.get(j).getCordx()][listOfFields.get(j).getCordy()].getLetter();
            }
            boolean isIt = false;
            for(int j = 0; j < dictionary.size(); j++){
                if(word.equals(dictionary.get(j))){
                    isIt = true;
                    break;
                }
            }
            if (isIt == false){
                return false;
            }
        }
        return true;
    }

    public int howManyPoints(PossibleWords word){
        int points = 0;
        int bonus = 1;
        int n = 0;
        //LetterPoints letterPoints = new LetterPoints();

        //poziomo
        if (word.getStartField().getCordx() == word.getEndField().getCordx()){
            for(int i = word.getStartField().getCordy(); i <= word.getEndField().getCordy(); i++){
                if (board.getBoard()[word.getStartField().getCordx()][i].getBonus() == 0) {
                    points = points + letterPoints.getLetterWithPoints().get(Character.toString(word.getWord().charAt(n)));
                }
                if (board.getBoard()[word.getStartField().getCordx()][i].getBonus() == 1) {
                    points = points + 2 * letterPoints.getLetterWithPoints().get(Character.toString(word.getWord().charAt(n)));
                }
                if (board.getBoard()[word.getStartField().getCordx()][i].getBonus() == 2) {
                    points = points + 3 * letterPoints.getLetterWithPoints().get(Character.toString(word.getWord().charAt(n)));
                }
                if (board.getBoard()[word.getStartField().getCordx()][i].getBonus() == 3) {
                    points = points + letterPoints.getLetterWithPoints().get(Character.toString(word.getWord().charAt(n)));
                    bonus = bonus * 2;
                }
                if (board.getBoard()[word.getStartField().getCordx()][i].getBonus() == 4) {
                    points = points + letterPoints.getLetterWithPoints().get(Character.toString(word.getWord().charAt(n)));
                    bonus = bonus * 3;
                }
                n = n + 1;
                if (n < 0) {
                    break;
                }
            }
        }else {
            for(int i = word.getStartField().getCordx(); i <= word.getEndField().getCordx(); i++){
                if (board.getBoard()[i][word.getStartField().getCordy()].getBonus() == 0) {
                    points = points + letterPoints.getLetterWithPoints().get(Character.toString(word.getWord().charAt(n)));
                }
                if (board.getBoard()[i][word.getStartField().getCordy()].getBonus() == 1) {
                    points = points + 2 * letterPoints.getLetterWithPoints().get(Character.toString(word.getWord().charAt(n)));
                }
                if (board.getBoard()[i][word.getStartField().getCordy()].getBonus() == 2) {
                    points = points + 3 * letterPoints.getLetterWithPoints().get(Character.toString(word.getWord().charAt(n)));
                }
                if (board.getBoard()[i][word.getStartField().getCordy()].getBonus() == 3) {
                    points = points + letterPoints.getLetterWithPoints().get(Character.toString(word.getWord().charAt(n)));
                    bonus = bonus * 2;
                }
                if (board.getBoard()[i][word.getStartField().getCordy()].getBonus() == 4) {
                    points = points + letterPoints.getLetterWithPoints().get(Character.toString(word.getWord().charAt(n)));
                    bonus = bonus * 3;
                }
                n = n + 1;
                if (n < 0) {
                    break;
                }
            }
        }

        points = points * bonus;
        return points;
    }

    public Player getPLayer(){
        return listOfPlayers.get(currentPlayer);
    }

    public Player getPLayerByIndex(int index){
        if (index < 0 || index > listOfPlayers.size() - 1){
            return listOfPlayers.get(0);
        }else{
            return listOfPlayers.get(index);
        }
    }

    public void changePlayer(){
        if(endOfTheGame()){
            return;
        }
        if(currentPlayer == listOfPlayers.size() - 1){
            currentPlayer = 0;
        }else {
            currentPlayer = currentPlayer + 1;
        }
        if (listOfPlayers.get(currentPlayer).getAvaibleLetters().size() == 0 && lettersInBox.howManyLeft() == 0){
            listOfPlayers.get(currentPlayer).eliminate();
        }
        if (listOfPlayers.get(currentPlayer).isEliminated()){
            changePlayer();
        }
    }

    public void changeLetter(int letterIndex){
        Player player = getPLayer();
        if(player.getIsItHuman()){
            int pointsCouldGet = maxPointsHumanCouldGet(player);
            player.setMaxPointsPlayerCouldGet(player.getMaxPointsPlayerCouldGet() + pointsCouldGet);
        }
        player.changeLetter(lettersInBox, letterIndex);
        player.setTurnsMissingPlay(player.getTurnsMissingPlay() + 1);
        if (player.getTurnsMissingPlay() == 3){
            player.eliminate();
        }
        //changePlayer();
    }

    public void changeLetters(ArrayList<String> letterIndex){
        Player player = getPLayer();
        if(player.getIsItHuman()){
            int pointsCouldGet = maxPointsHumanCouldGet(player);
            player.setMaxPointsPlayerCouldGet(player.getMaxPointsPlayerCouldGet() + pointsCouldGet);
        }
        player.changeLetters(lettersInBox, letterIndex);
        player.setTurnsMissingPlay(player.getTurnsMissingPlay() + 1);
        if (player.getTurnsMissingPlay() == 3){
            player.eliminate();
        }
        //changePlayer();
    }

    public boolean endOfTheGame(){
        boolean allPlayersEliminated = true;
        for (int i = 0; i < listOfPlayers.size(); i++){
            if (!listOfPlayers.get(i).isEliminated()){
                allPlayersEliminated = false;
            }
        }
        if (allPlayersEliminated){
            return true;
        }
        return false;
    }

    public int maxPointsHumanCouldGet(Player player){
        int maxPoints = 0;
        if(turn == 0) {
            TrueWordFinder trueWordFinder = new TrueWordFinder(list);
            trueWordFinder.FindWordForEmptyBoard(player.getAvaibleLetters(), board);
            listWithAllWordsWithPoints = trueWordFinder.getListWithAllWordsWithPoints();

            if (listWithAllWordsWithPoints.size() == 0){
                return 0;
            }

            ArrayList<Integer> listWithPointsToSelect = new ArrayList<>();
            for (int i = 0 ; i < 100; i++){
                if (listWithAllWordsWithPoints.containsKey(i)){
                    listWithPointsToSelect.add(i);
                }
            }
            Collections.sort(listWithPointsToSelect);
            maxPoints = listWithPointsToSelect.get(listWithPointsToSelect.size() - 1);

        }else{
            HorizontalFieldsFinder horizontalFieldsFinder = new HorizontalFieldsFinder(board);
            VerticalFieldsFinder verticalFieldsFinder = new VerticalFieldsFinder(board);
            List<List<Word>> listOfPossibleWords1 = verticalFieldsFinder.findAllVerticalPossibleWords();
            List<List<Word>> listOfPossibleWords2 = horizontalFieldsFinder.findAllHorizontalPossibleWords();
            TrueWordFinder trueWordFinder = new TrueWordFinder(listOfPossibleWords1);
            trueWordFinder.FindAllWorlds(player.getAvaibleLetters(), board);
            trueWordFinder.setListWithAllPossibleFields(listOfPossibleWords2);
            trueWordFinder.FindAllWorlds(player.getAvaibleLetters(), board);
            listWithAllWordsWithPoints = trueWordFinder.getListWithAllWordsWithPoints();

            if (listWithAllWordsWithPoints.size() == 0){
                return 0;
            }

            ArrayList<Integer> listWithPointsToSelect = new ArrayList<>();
            for (int i = 0 ; i < 100; i++){
                if (listWithAllWordsWithPoints.containsKey(i)){
                    listWithPointsToSelect.add(i);
                }
            }
            Collections.sort(listWithPointsToSelect);
            maxPoints = listWithPointsToSelect.get(listWithPointsToSelect.size() - 1);
        }

        return maxPoints;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void showMap(){
        board.show();
    }

    public int isThereAnyToDraw(){
        return lettersInBox.howManyLeft();
    }

    public Board getBoard() {
        return board;
    }

    /*public Player getPlayer1() {
        return Player1;
    }

    public Player getPlayer2() {
        return Player2;
    }

    public Player getPlayer3() {
        return Player3;
    }

    public Player getPlayer4() {
        return Player4;
    }*/

    public LettersInBox getLettersInBox() {
        return lettersInBox;
    }

    public static void main(String[] args) {
        PlayerHuman playerHuman1 = new PlayerHuman();
        PlayerHuman playerHuman2 = new PlayerHuman();
        PlayerAi playerAi1 = new PlayerAi(1);
        PlayerAi playerAi2 = new PlayerAi(2);
        ArrayList<Player> listPlayers = new ArrayList<Player>();
        listPlayers.add(playerHuman1);
        listPlayers.add(playerHuman2);
        Game game = new Game(listPlayers);
        //Game game = new Game(playerHuman1, playerAi1, null, null);
        game.showMap();
        game.play(new PossibleWords(new Field("", 0, 7, 7, false), new Field("", 0, 9, 7, false), "typ"));
        game.changePlayer();
        game.showMap();
        game.play(new PossibleWords(new Field("", 0, 9, 7, false), new Field("", 0, 9, 9, false), "pet"));
        game.changePlayer();
        game.showMap();
        game.play(new PossibleWords(new Field("", 0, 9, 9, false), new Field("", 0, 11, 9, false), "tor"));
        game.changePlayer();
        game.showMap();
        game.play(new PossibleWords(new Field("", 0, 4, 10, false), new Field("", 0, 7, 10, false), "nut"));
        game.changePlayer();
        game.showMap();
        game.play(new PossibleWords(new Field("", 0, 7, 7, false), new Field("", 0, 9, 7, false), "sen"));
        game.changePlayer();
        game.showMap();
        /*game.play(new PossibleWords(new Field("", 0, 7, 7, false), new Field("", 0, 7, 9, false), "sam"));
        game.changePlayer();
        game.showMap();
        game.play(new PossibleWords(new Field("", 0, 7, 7, false), new Field("", 0, 7, 10, false), "sama"));
        game.changePlayer();
        game.showMap();
        game.play(new PossibleWords(new Field("", 0, 4, 10, false), new Field("", 0, 7, 10, false), "dupa"));
        game.changePlayer();
        game.showMap();*/
        /*
        game.play(null);
        game.showMap();
        game.play(null);
        game.showMap();
        game.play(null);
        game.showMap();
        game.play(null);
        game.showMap();
        game.play(null);
        game.showMap();
        game.play(null);
        game.showMap();
        */



    }
}
