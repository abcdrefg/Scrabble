package database;

import User.Statistics;
import User.User;

import javax.xml.crypto.Data;
import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DatabaseConnection {
    private static final String DATABASE_URL = "jdbc:sqlite:src\\database\\ScrabbleDatabase.db";
    private static final String DRIVER_CLASS_NAME = "org.sqlite.JDBC";
    private static final String SEARCH_FOR_USER_QUERY = "SELECT userId, login, password FROM users WHERE login = ? AND password = ?";
    private static final String LOGIN_COLUMN = "login";
    private static final String EASY_AI_WINS_COLUMN = "easyAIwins";
    private static final String MEDIUM_AI_WINS_COLUMN = "mediumAIwins";
    private static final String HARD_AI_WINS_COLUMN = "hardAIwins";
    private static final String EASY_AI_GAMES_COLUMN = "easyAIgames";
    private static final String MEDIUM_AI_GAMES_COLUMN = "mediumAIgames";
    private static final String HARD_AI_GAMES_COLUMN = "hardAIgames";
    private static final String USER_ID_COLUMN = "userId";
    public static final String EASY = "EASY";
    public static final String MEDIUM = "MEDIUM";
    public static final String HARD = "HARD";
    private static final String SCORED_POINTS_COLUMN = "scoredPoints";
    private static final String ALL_POSSIBLE_POINTS_TO_SCORE_COLUMN = "allPossiblePointsToScore";
    public static final String PVP = "PVP";
    private static final String PVP_GAMES_COLUMN = "pvpGames";
    private static final String PVP_WINS_COLUMN = "pvpWins";
    private Connection connection;

    public DatabaseConnection() throws ClassNotFoundException {

        Class.forName(DRIVER_CLASS_NAME);
    }

    public Optional<User> getUserParams(String login, String password) throws SQLException {
        setConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_FOR_USER_QUERY);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;
        while (resultSet.next()) {
            int userId = resultSet.getInt(USER_ID_COLUMN);
            user = new User(login, userId);
        }
        closeConnection();
        return Optional.ofNullable(user);
    }

    public boolean createNewUser(String login, String password) throws SQLException {
        if (checkIfLoginExist(login)) {
            return false;
        } else {
            String sqlQuery = "INSERT INTO USERS (login, password) VALUES (?, ?)";
            setConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            boolean isUserCreated = preparedStatement.executeUpdate() == 1;
            closeConnection();
            return isUserCreated;
        }
    }

    public Statistics getUserStatistics(String login) throws SQLException {
        String sqlQuery = "SELECT easyAIwins, mediumAIwins, hardAIwins, easyAIgames, mediumAIgames, hardAIgames, scoredPoints,  allPossiblePointsToScore" +
                " FROM statistics, Users WHERE statistics.userId = Users.userId AND Users.login = ?";
        setConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();
        Statistics statistics = null;
        while (resultSet.next()) {
            statistics = new Statistics(resultSet.getInt(EASY_AI_GAMES_COLUMN), resultSet.getInt(MEDIUM_AI_GAMES_COLUMN), resultSet.getInt(HARD_AI_GAMES_COLUMN));
            statistics.setWinsVersusAI(resultSet.getInt(EASY_AI_WINS_COLUMN), resultSet.getInt(MEDIUM_AI_WINS_COLUMN), resultSet.getInt(HARD_AI_WINS_COLUMN));
            statistics.setPointFactor(resultSet.getInt(SCORED_POINTS_COLUMN), resultSet.getInt(ALL_POSSIBLE_POINTS_TO_SCORE_COLUMN));
            statistics.setPlayerLogin(login);
        }
        return statistics;
    }

    public List<Statistics> getAllStatistics() throws SQLException {
        String sqlQuery = "SELECT easyAIgames, mediumAIgames, hardAIgames, easyAIwins, mediumAIwins, hardAIwins, " +
                "scoredPoints,  allPossiblePointsToScore, Users.login, pvpWins, pvpGames FROM statistics, Users WHERE statistics.userId = Users.userId";
        setConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        List<Statistics> listOfStatistics = new ArrayList<>();
        while (resultSet.next()) {
            Statistics statistics = new Statistics(resultSet.getInt(EASY_AI_GAMES_COLUMN), resultSet.getInt(MEDIUM_AI_GAMES_COLUMN), resultSet.getInt(HARD_AI_GAMES_COLUMN));
            statistics.setWinsVersusAI(resultSet.getInt(EASY_AI_WINS_COLUMN), resultSet.getInt(MEDIUM_AI_WINS_COLUMN), resultSet.getInt(HARD_AI_WINS_COLUMN));
            statistics.setStatsAgainstHuman(resultSet.getInt(PVP_WINS_COLUMN), resultSet.getInt(PVP_GAMES_COLUMN));
            statistics.setPointFactor(resultSet.getInt(SCORED_POINTS_COLUMN), resultSet.getInt(ALL_POSSIBLE_POINTS_TO_SCORE_COLUMN));
            statistics.setPlayerLogin(resultSet.getString(LOGIN_COLUMN));
            statistics.setWinPercentages();
            statistics.setPointFactor();
            listOfStatistics.add(statistics);
        }
        closeConnection();
        return listOfStatistics;
    }

    public boolean updateUserStatistics(boolean isGameWon, String gameMode, int scoredPoints, int allPossiblePointsToScore, String login) throws SQLException {
        String sqlQuery = "UPDATE statistics SET {0} = {0} + ?, {1} = {1} + ?, scoredPoints = scoredPoints + ?, allPossiblePointsToScore = allPossiblePointsToScore + ?  WHERE userId = (SELECT userId FROM Users WHERE login = ?)";
        int incrementWonGamesColumn = isGameWon ? 1 : 0;
        setConnection();
        switch (gameMode) {
            case EASY -> statisticsUpdaterHelper(incrementWonGamesColumn, scoredPoints, allPossiblePointsToScore, login, sqlQuery, EASY_AI_GAMES_COLUMN, EASY_AI_WINS_COLUMN);
            case MEDIUM -> statisticsUpdaterHelper(incrementWonGamesColumn, scoredPoints, allPossiblePointsToScore, login, sqlQuery, MEDIUM_AI_GAMES_COLUMN, MEDIUM_AI_WINS_COLUMN);
            case HARD -> statisticsUpdaterHelper(incrementWonGamesColumn, scoredPoints, allPossiblePointsToScore, login, sqlQuery, HARD_AI_GAMES_COLUMN, HARD_AI_WINS_COLUMN);
            case PVP -> statisticsUpdaterHelper(incrementWonGamesColumn, scoredPoints, allPossiblePointsToScore, login, sqlQuery, PVP_GAMES_COLUMN, PVP_WINS_COLUMN);
        }
        closeConnection();
        return true;
    }

    private void statisticsUpdaterHelper(int incrementWonGamesColumn, int scoredPoints, int allPossiblePointsToScore, String login, String sqlQuery, String... columnNames) throws SQLException {
        String formattedQuery = MessageFormat.format(sqlQuery, columnNames[0], columnNames[1]);
        PreparedStatement preparedStatement = connection.prepareStatement(formattedQuery);
        preparedStatement.setInt(1, 1);
        preparedStatement.setInt(2, incrementWonGamesColumn);
        preparedStatement.setDouble(3, scoredPoints);
        preparedStatement.setInt(4, allPossiblePointsToScore);
        preparedStatement.setString(5, login);
        preparedStatement.executeUpdate();
    }

    public boolean checkIfUserExist(String login, String password) throws SQLException {
        setConnection();
        String sqlQuery = "SELECT login, password FROM users WHERE login = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean isUserExist = resultSet.next();
        closeConnection();
        return isUserExist;
    }

    public boolean changePassword(String login, String password, String newPassword) throws SQLException{
        if (checkIfUserExist(login, password)){
            setConnection();
            String sqlQuery = "UPDATE users SET password = ? WHERE login = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, login);
            int value = preparedStatement.executeUpdate();
            closeConnection();
            return value > 0;
        }else {
            closeConnection();
            return false;
        }
    }

    private boolean checkIfLoginExist(String login) throws SQLException {
        setConnection();
        String sqlQuery = "SELECT login FROM users WHERE login = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean isLoginExist = resultSet.next();
        closeConnection();
        return isLoginExist;
    }

    private void setConnection() throws SQLException {
        connection = DriverManager.getConnection(DATABASE_URL);
    }

    private void closeConnection() throws SQLException {
        connection.close();
    }
}
