package gui;

public class GuiMove {
    private final int xCord;
    private final int yCord;
    private final String letter;

    public GuiMove(int xCord, int yCord, String letter){
        this.xCord = xCord;
        this.yCord = yCord;
        this.letter = letter;
    }

    public int getxCord() {
        return xCord;
    }

    public int getyCord() {
        return yCord;
    }

    public String getLetter() {
        return letter;
    }
}
