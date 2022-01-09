package ScrabbleBoard;

public class Field {
    private String letter;
    private int bonus;
    private int cordx;
    private int cordy;
    private boolean isEmpty;

    public Field(String letter, int bounus, int x, int y, boolean isEmpty) {
        this.letter = letter;
        this.bonus = bounus;
        this.cordx = x;
        this.cordy = y;
        this.isEmpty = isEmpty;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
        this.isEmpty =false;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }

    public void setCordx(int cordx) {
        this.cordx = cordx;
    }

    public void setCordy(int cordy) {
        this.cordy = cordy;
    }

    public int getCordx() {
        return cordx;
    }

    public int getCordy() {
        return cordy;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
}