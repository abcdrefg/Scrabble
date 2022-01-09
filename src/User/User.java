package User;

public class User {
    private final String login;
    private final int userId;
    private Statistics statistics;
    public User(String login, int userId){
        this.login = login;
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public int getUserId() {
        return userId;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }
}
