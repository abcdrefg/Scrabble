package ScrabbleBoard;

import wordFinder.PossibleWords;

import java.util.Locale;

public class Board {
    public Field[][] board;

    public Board(){
        this.board = new Field[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                board[i][j] = new Field(" ", 0, i, j,true);
            }
        }
        //red bonus potrojna slowna
        for(int i=0; i < 15; i= i + 7) {
            for(int j=0; j < 15; j= j + 7) {
                this.board[i][j].setBonus(4);
            }
        }
        this.board[7][7].setBonus(0);
        //yellow bonus podwojna slowna
        for(int j=0; j < 4; j++) {
            this.board[1 + j][1 + j].setBonus(3);
        }
        for(int j=0; j < 4; j++) {
            this.board[13 - j][13 - j].setBonus(3);
        }
        for(int j=0; j < 4; j++) {
            this.board[13 - j][1 + j].setBonus(3);
        }
        for(int j=0; j < 4; j++) {
            this.board[1 + j][13 - j].setBonus(3);
        }
        //blue bonus potrojna literowa
        for(int i=5; i < 10; i= i + 4) {
            for(int j=1; j < 15; j= j + 4) {
                this.board[i][j].setBonus(2);
            }
        }
        for(int i=1; i < 15; i= i + 4) {
            for(int j=5; j < 10; j= j + 4) {
                this.board[i][j].setBonus(2);
            }
        }
        //white bonus podwojna literowa
        this.board[0][3].setBonus(1);
        this.board[0][11].setBonus(1);
        this.board[14][3].setBonus(1);
        this.board[14][11].setBonus(1);
        this.board[3][0].setBonus(1);
        this.board[11][0].setBonus(1);
        this.board[3][14].setBonus(1);
        this.board[11][14].setBonus(1);

        this.board[6][6].setBonus(1);
        this.board[6][8].setBonus(1);
        this.board[8][6].setBonus(1);
        this.board[8][8].setBonus(1);

        this.board[6][2].setBonus(1);
        this.board[6][12].setBonus(1);
        this.board[8][2].setBonus(1);
        this.board[8][12].setBonus(1);
        this.board[7][3].setBonus(1);
        this.board[7][11].setBonus(1);
        this.board[2][6].setBonus(1);
        this.board[12][6].setBonus(1);
        this.board[2][8].setBonus(1);
        this.board[12][8].setBonus(1);
        this.board[3][7].setBonus(1);
        this.board[11][7].setBonus(1);
    }

    public void show(){
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                //
                System.out.print(board[i][j].getLetter());
                //System.out.print(String.valueOf(board[i][j].getBonus()));
            }
            System.out.println("");
        }
    }

    public void changeLettersInBoard(PossibleWords whichWord){
        if (whichWord.getStartField().getCordx() == whichWord.getEndField().getCordx()) {
            String word = whichWord.getWord().toLowerCase(Locale.ROOT);
            int j = 0;
            for (int i = whichWord.getStartField().getCordy(); i <= whichWord.getEndField().getCordy(); i++) {
                board[whichWord.getEndField().getCordx()][i].setLetter(Character.toString(word.charAt(j)));
                if(Character.toString(word.charAt(j)).equals("-")){
                    board[whichWord.getEndField().getCordx()][i].setEmpty(true);
                }
                j++;
            }
        }
        if (whichWord.getStartField().getCordy() == whichWord.getEndField().getCordy()) {
            String word = whichWord.getWord().toLowerCase(Locale.ROOT);
            int j = 0;
            for (int i = whichWord.getStartField().getCordx(); i <= whichWord.getEndField().getCordx(); i++) {
                board[i][whichWord.getEndField().getCordy()].setLetter(Character.toString(word.charAt(j)));
                if(Character.toString(word.charAt(j)).equals("-")){
                    board[i][whichWord.getEndField().getCordy()].setEmpty(true);
                }
                j++;
            }
        }

        correctingBoard();
    }

    public Field[][] getBoard() {
        return board;
    }

    public void correctingBoard(){
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if(this.board[i][j].getLetter().equals(" ")){
                    this.board[i][j].setEmpty(true);
                }else {
                    this.board[i][j].setEmpty(false);
                }
            }
        }
    }

    public static void main(String[] args){
        Board board = new Board();
        board.show();
    }
}
