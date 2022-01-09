package User;

public class Statistics {
    private String playerLogin;
    private int easyAIgames;
    private int mediumAIgames;
    private int hardAIgames;
    private int easyAIwins;
    private int mediumAIwins;
    private int hardAIwins;
    private int totalGames;
    private int scoredPoints;
    private int allPossiblePointsToScore;
    private int pvpWins;
    private int pvpGames;
    private double easyAIwinPercent;
    private double mediumAIwinPercent;
    private double hardAIwinPercent;
    private double pvpWinPercent;
    private double pointFactor;

    public Statistics(int easyAIgames, int mediumAIgames, int hardAIgames) {
        this.easyAIgames = easyAIgames;
        this.mediumAIgames = mediumAIgames;
        this.hardAIgames = hardAIgames;
    }

    public void setWinsVersusAI(int easyAIwins, int mediumAIwins, int hardAIwins) {
        this.easyAIwins = easyAIwins;
        this.mediumAIwins = mediumAIwins;
        this.hardAIwins = hardAIwins;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    public int getScoredPoints() {
        return scoredPoints;
    }

    public int getAllPossiblePointsToScore() {
        return allPossiblePointsToScore;
    }

    public void setPointFactor(int scoredPoints, int allPossiblePointsToScore){
        this.scoredPoints = scoredPoints;
        this.allPossiblePointsToScore = allPossiblePointsToScore;
    }

    public double getEasyAIwinPercent() {
        return easyAIwinPercent;
    }

    public double getMediumAIwinPercent() {
        return mediumAIwinPercent;
    }

    public double getHardAIwinPercent() {
        return hardAIwinPercent;
    }

    public double getPointFactor(){
        return allPossiblePointsToScore == 0 ? 0 : (double)scoredPoints/allPossiblePointsToScore;
    }

    public void setPlayerLogin(String playerLogin){
        this.playerLogin = playerLogin;
    }

    public String getPlayerLogin(){
        return playerLogin;
    }


    public void setWinPercentages(){
        easyAIwinPercent = easyAIgames == 0 ? 0 : (double)easyAIwins/easyAIgames;
        mediumAIwinPercent = mediumAIgames == 0 ? 0 : (double)mediumAIwins/mediumAIgames;
        hardAIwinPercent = hardAIgames == 0 ? 0 : (double)hardAIwins/hardAIgames;
        pvpWinPercent = pvpGames == 0? 0: (double) pvpWins/pvpGames;
    }

    public void setPointFactor(){
        pointFactor = allPossiblePointsToScore == 0 ? 0: (double)scoredPoints/allPossiblePointsToScore;
    }

    public void setStatsAgainstHuman(int pvpWins, int pvpGames){

        this.pvpWins = pvpWins;
        this.pvpGames = pvpGames;
    }

    public int getEasyAIgames() {
        return easyAIgames;
    }

    public int getMediumAIgames() {
        return mediumAIgames;
    }

    public int getHardAIgames() {
        return hardAIgames;
    }

    public int getEasyAIwins() {
        return easyAIwins;
    }

    public int getMediumAIwins() {
        return mediumAIwins;
    }

    public int getHardAIwins() {
        return hardAIwins;
    }

    public int getTotalGames() {
        return totalGames;
    }



    public int getPvpWins() {
        return pvpWins;
    }

    public int getPvpGames() {
        return pvpGames;
    }

    public double getPvpWinPercent() {
        return pvpWinPercent;
    }

    public void printStatistics(){
        System.out.println("Player: " + getPlayerLogin()+ "\n"+"Easy" + "\n" + "Wins: " + getEasyAIwins() + "\n"
                + "All games: " + getEasyAIgames() + "\n" + "Medium" + "\n" + "Wins: " + getMediumAIwins() + "\n"
                + "All games: " + getMediumAIgames() + "\n" + "Hard" + "\n" + "Wins: " + getHardAIwins() + "\n"
                + "All games: " + getHardAIgames() + "\n"+ "Point factor: " + getPointFactor() + "\n"
        + "Human " + getPvpGames() + " Wins " + getPvpWins());
    }

}
