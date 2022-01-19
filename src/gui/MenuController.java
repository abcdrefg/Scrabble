package gui;

import User.Statistics;
import User.User;
import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class MenuController {

    private static final String PATH_TO_PLAYER_MENU = "fxml/SelectPlayers.fxml";
    private static final String PATH_TO_AI_MENU = "fxml/AIMenu.fxml";
    private static final String PATH_TO_STATISTICS = "fxml/Statistics.fxml";
    private static final String PATH_TO_LOGIN = "fxml/Login.fxml";
    private static DatabaseConnection databaseConnection;
    private static User user;

    @FXML
    private Button statisticsButton;

    @FXML
    private Button playWithAIButton;

    @FXML
    private Button playWithFriendsButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button changePasswordButton;

    @FXML
    private Button logoutButton;

    @FXML
    void changePassword(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/ChangePassword.fxml"));
        ControllersHelper.changeScene(changePasswordButton, fxmlLoader);
        ChangePasswordController changePasswordController = fxmlLoader.getController();
        changePasswordController.setUser(user);
    }

    @FXML
    void logout(ActionEvent event) {
        user = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PATH_TO_LOGIN));
        ControllersHelper.changeScene(logoutButton, fxmlLoader);
    }

    @FXML
    void openStatisticsPage(ActionEvent event) {
        initializeDatabase();
        try {
            List<Statistics> listOfStatistics = databaseConnection.getAllStatistics();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PATH_TO_STATISTICS));
            ControllersHelper.changeScene(statisticsButton, fxmlLoader);
            StatisticsController statisticsController = fxmlLoader.getController();
            statisticsController.initializeStatistics(listOfStatistics);
        } catch (SQLException e) {
            new AlertHandler().display(Alert.AlertType.ERROR, AlertHandler.ERROR, AlertHandler.DATABASE_ERROR, e.getMessage());
        }

    }

    @FXML
    void playWithAI(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PATH_TO_AI_MENU));
        ControllersHelper.changeScene(playWithAIButton, fxmlLoader);
    }

    @FXML
    void playWithFriends(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PATH_TO_PLAYER_MENU));
        ControllersHelper.changeScene(playWithFriendsButton, fxmlLoader);
        SelectPlayersController selectPlayersController = fxmlLoader.getController();
        selectPlayersController.setUser(user);
    }

    @FXML
    void exitGame(ActionEvent event) {
        Stage window = (Stage) (exitButton.getScene().getWindow());
        window.close();
    }

    private void initializeDatabase() {
        try {
            databaseConnection = new DatabaseConnection();
        } catch (ClassNotFoundException e) {
            new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.ERROR, AlertHandler.DATABASE_ERROR, e.getMessage());
        }
    }

    public void setUser(User user) {
        MenuController.user = user;
    }

}

