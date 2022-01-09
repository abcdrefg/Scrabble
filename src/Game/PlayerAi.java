package Game;

public class PlayerAi extends Player{
    private int level;
    private String aiPlayerLevel;

    public PlayerAi(int level){
        super(false);
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public String getAiPlayerLevel() {
        return aiPlayerLevel;
    }

    public void setAiPlayerLevel(String aiPlayerLevel) {
        this.aiPlayerLevel = aiPlayerLevel;
    }
}
