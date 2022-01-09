package gui;

import ScrabbleBoard.Board;
import ScrabbleBoard.Field;
import javafx.scene.AccessibleAction;
import javafx.scene.control.Button;
import java.util.ArrayList;
import java.util.Collections;

public class LetterPlacingChecker {

    public static boolean isMovePossible(Board board, int x, int y){
        if (x == 7 && y == 7 && (board.board[x][y].isEmpty() == true)){
            return true;
        }
        if (board.board[x][y].isEmpty() == false) {
            return false;
        }
        if (x != 0) {
            if (board.board[x - 1][y].isEmpty() == false) {
                return true;
            }
        }
        if (x != 14){
            if (board.board[x+1][y].isEmpty() == false) {
                return true;
            }
        }
        if (y != 0){
            if (board.board[x][y-1].isEmpty() == false) {
                return true;
            }
        }
        if (y != 14) {
            if (board.board[x][y + 1].isEmpty() == false) {
                return true;
            }
        }
        return false;
    }
    public static boolean isLetterConnectedWithPrevious(ArrayList<GuiMove> moves, GuiMove move, Board board){
        int neighboursCounter = 0;
        if (moves.size() != 0) {
            neighboursCounter = countNeighbours(board, moves.get(0).getxCord(), moves.get(0).getyCord());
        }
        if (moves.size() == 0) {
            neighboursCounter = countNeighbours(board, move.getxCord(), move.getyCord());
        }
        Boolean checker = false;
        if (neighboursCounter == 2){
            if (moves.size() == 0){
                return true;
            }
            if (moves.size() == 1){
                if (isNeighboursInLine(board, moves.get(0).getxCord(), moves.get(0).getyCord())){
                    return false;
                }
                return true;
            } else {
                checker = false;
                if (moves.get(moves.size()-1).getxCord() == moves.get(moves.size()-2).getxCord()) {
                    if (move.getxCord() == moves.get(moves.size()-1).getxCord()) {
                        checker = true;
                    }
                }
                if (moves.get(moves.size()-1).getyCord() == moves.get(moves.size()-2).getyCord()) {
                    if (move.getyCord() == moves.get(moves.size()-1).getyCord()) {
                        checker = true;
                    }
                }
                return checker;
            }

        }else {
            GuiMove firstField;
            if (moves.size() > 0 && neighboursCounter != 3) {
                firstField = getOneNeighbour(board, moves.get(0).getxCord(), moves.get(0).getyCord());
                moves.add(firstField);
            }
            if (moves.size() == 0) {
                return true;
            }
            if (moves.size() == 1) {
                if (moves.get(0).getxCord() == move.getxCord() || moves.get(0).getyCord() == move.getyCord()) {
                    checker = true;
                }
            } else {
                if (moves.get(0).getxCord() == moves.get(1).getxCord()) {
                    if (move.getxCord() == moves.get(1).getxCord()) {
                        checker = true;
                    }
                }
                if (moves.get(0).getyCord() == moves.get(1).getyCord()) {
                    if (move.getyCord() == moves.get(1).getyCord()) {
                        checker = true;
                    }
                }
            }
            Boolean isNotConnected = true;
            for (int i = 0; i < moves.size(); i++) {
                int x = moves.get(i).getxCord();
                int y = moves.get(i).getyCord();
                if (((move.getxCord() == x - 1) || (move.getxCord() == x + 1)) && (move.getyCord() == y)) {
                    isNotConnected = false;
                }
                if (((move.getyCord() == y - 1) || (move.getyCord() == y + 1)) && (move.getxCord() == x)) {
                    isNotConnected = false;
                }
            }
            if (isNotConnected == true) {
                checker = false;
            }
        }
        return checker;
    }
    public static int countNeighbours(Board board, int x, int y) {
        int counter = 0;
        if (x != 0) {
            if (board.board[x - 1][y].isEmpty() == false) {
                counter = counter + 1;
            }
        }
        if (x != 14){
            if (board.board[x+1][y].isEmpty() == false) {
                counter = counter + 1;
            }
        }
        if (y != 0){
            if (board.board[x][y-1].isEmpty() == false) {
                counter = counter + 1;
            }
        }
        if (y != 14) {
            if (board.board[x][y + 1].isEmpty() == false) {
                counter = counter + 1;
            }
        }
        return counter;
    }
    public static boolean isNeighboursInLine(Board board, int x, int y) {
        ArrayList<Field> neighbours = new ArrayList<>();
        if (x != 0) {
            if (board.board[x - 1][y].isEmpty() == false) {
                neighbours.add(board.board[x - 1][y]);
            }
        }
        if (x != 14){
            if (board.board[x + 1][y].isEmpty() == false) {
                neighbours.add(board.board[x + 1][y]);
            }
        }
        if (y != 0){
            if (board.board[x][y - 1].isEmpty() == false) {
                neighbours.add(board.board[x][y - 1]);
            }
        }
        if (y != 14) {
            if (board.board[x][y + 1].isEmpty() == false) {
                neighbours.add(board.board[x][y + 1]);
            }
        }
        Boolean neighboursInLineCheck = false;
        for (int i = 0 ; i < neighbours.size(); i++){
            if (i == 0){
                continue;
            }
            if (neighbours.get(i-1).getCordx() == neighbours.get(i).getCordx()){
                neighboursInLineCheck = true;
            }
            if (neighbours.get(i-1).getCordy() == neighbours.get(i).getCordy()){
                neighboursInLineCheck = true;
            }
        }
        return neighboursInLineCheck;
    }
    public static GuiMove getOneNeighbour(Board board, int x, int y) {
        if (x != 0) {
            if (board.board[x - 1][y].isEmpty() == false) {
                GuiMove startField = new GuiMove(x - 1, y, board.board[x - 1][y].getLetter());
                return startField;
            }
        }
        if (x != 14){
            if (board.board[x+1][y].isEmpty() == false) {
                GuiMove startField = new GuiMove(x + 1, y, board.board[x + 1][y].getLetter());
                return startField;
            }
        }
        if (y != 0){
            if (board.board[x][y-1].isEmpty() == false) {
                GuiMove startField = new GuiMove(x, y - 1, board.board[x][y - 1].getLetter());
                return startField;
            }
        }
        if (y != 14) {
            if (board.board[x][y + 1].isEmpty() == false) {
                GuiMove startField = new GuiMove(x, y + 1, board.board[x][y + 1].getLetter());
                return startField;
            }
        }
        GuiMove startField = new GuiMove(0,0,"");
        return startField;
    }
    public static boolean checkWord(Board board, ArrayList<GuiMove> moves){
        boolean inlineWithOXAxis = false;
        int wordBegin = 0;
        int wordEnd = 0;
        if (moves.size() == 0){
            System.out.println("słowo jest 0");
            return false;
        }
        if (moves.size() == 1){
            System.out.println("słowo jest 1");
            return true;
        }
        if (moves.get(0).getxCord() == moves.get(1).getxCord()){
            inlineWithOXAxis = true;
        } else {
            if (moves.get(0).getyCord() == moves.get(1).getyCord()){
                inlineWithOXAxis = false;
            } else {
                System.out.println("nie są inline");
                return false;
            }
        }
        if (inlineWithOXAxis) {
            for (int i = 0 ; i < moves.size() ; i++){
                if (moves.get(i).getxCord() != moves.get(0).getxCord()) {
                    System.out.println("nie wszystkie są inline x");
                    return false;
                }
            }
        } else {
            for (int i = 0 ; i < moves.size() ; i++){
                if (moves.get(i).getyCord() != moves.get(0).getyCord()) {
                    System.out.println("nie wszystkie są inline y");
                    return false;
                }
            }
        }
        wordBegin = findBegining(board, moves, inlineWithOXAxis);
        wordEnd = findEnding(board, moves, inlineWithOXAxis);
        ArrayList<Integer> gapList = new ArrayList<>();
        ArrayList<Integer> moveCords = new ArrayList<>();
        if (inlineWithOXAxis){
            for(int i = 0 ; i < moves.size(); i++){
                moveCords.add(moves.get(i).getyCord());
            }
            Collections.sort(moveCords);
            for(int i = 0 ; i < moveCords.size(); i++){
                if (i == 0){
                    continue;
                } else {
                    if (moveCords.get(i-1) + 1 == moveCords.get(i)){
                        continue;
                    } else {
                        if(board.board[moves.get(0).getxCord()][moveCords.get(i-1) + 1].isEmpty()){
                            return false;
                        } else {
                            moveCords.add(moveCords.get(i-1) + 1);
                            Collections.sort(moveCords);
                        }
                    }
                }
            }
        } else {
            for(int i = 0 ; i < moves.size(); i++){
                moveCords.add(moves.get(i).getxCord());
            }
            Collections.sort(moveCords);
            for(int i = 0 ; i < moveCords.size(); i++){
                if (i == 0){
                    continue;
                } else {
                    if (moveCords.get(i-1) + 1 == moveCords.get(i)){
                        continue;
                    } else {
                        if(board.board[moveCords.get(i-1) + 1][moves.get(0).getyCord()].isEmpty()){
                            return false;
                        } else {
                            moveCords.add(moveCords.get(i-1) + 1);
                            Collections.sort(moveCords);
                        }
                    }
                }
            }
        }
        return true;
    }

    public static int findBegining(Board board, ArrayList<GuiMove> moves, Boolean inlineWithOXAxis){
        int wordBegin = 100;
        int x = 0;
        int y = 0;
        if (inlineWithOXAxis) {
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).getyCord() < wordBegin){
                    wordBegin = moves.get(i).getyCord();
                    x = moves.get(i).getxCord();
                    y = moves.get(i).getyCord();
                }
            }
        } else {
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).getxCord() < wordBegin){
                    wordBegin = moves.get(i).getxCord();
                    x = moves.get(i).getxCord();
                    y = moves.get(i).getyCord();
                }
            }
        }
        for (int j = 1 ; j < 1000 ; j++){
            if(inlineWithOXAxis){
                if(y-j > -1){
                    if(!board.getBoard()[x][y-j].isEmpty()){
                        wordBegin = y-j;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            } else {
                if(x-j > -1){
                    if(!board.getBoard()[x-j][y].isEmpty()){
                        wordBegin = x-j;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return wordBegin;
    }
    public static int findEnding(Board board, ArrayList<GuiMove> moves, Boolean inlineWithOXAxis){
        int wordEnd = 0;
        int x = 0;
        int y = 0;
        if (moves.size() == 1){
            GuiMove field = getOneNeighbour(board, moves.get(0).getxCord(), moves.get(0).getyCord());
            if (field.getxCord() == moves.get(0).getxCord()){
                inlineWithOXAxis = true;
            } else {
                inlineWithOXAxis = false;
            }
        }
        if (inlineWithOXAxis) {
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).getyCord() > wordEnd){
                    wordEnd = moves.get(i).getyCord();
                    x = moves.get(i).getxCord();
                    y = moves.get(i).getyCord();
                }
            }
        } else {
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).getxCord() > wordEnd){
                    wordEnd = moves.get(i).getxCord();
                    x = moves.get(i).getxCord();
                    y = moves.get(i).getyCord();
                }
            }
        }
        for (int j = 1 ; j < 1000 ; j++){
            if (inlineWithOXAxis){
                if (y + j < 15) {
                    if(!board.board[x][y + j].isEmpty()){
                        wordEnd = board.board[x][y + j].getCordy();
                    } else {
                        break;
                    }
                }
            }else {
                if (x + j < 15) {
                    if(!board.board[x + j][y].isEmpty()){
                        wordEnd = board.board[x + j][y].getCordx();
                    } else {
                        break;
                    }
                }
            }
        }
        return wordEnd;
    }
    public static String getWord(int wordBegin, int wordEnd, boolean inlineWithOx, Board board, int line){
        String word = "";
        if (inlineWithOx) {
            for (int i = wordBegin; i <= wordEnd; i++) {
                word = word + (board.getBoard()[line][i].getLetter());
            }
        } else {
            for (int i = wordBegin; i <= wordEnd; i++) {
                word = word + (board.getBoard()[i][line].getLetter());
            }
        }
        System.out.println(word);
        return word;
    }

}
