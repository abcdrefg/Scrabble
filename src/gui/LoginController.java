package gui;

import User.Statistics;
import User.User;
import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.Optional;

public class LoginController {

    private static final String PATH_TO_MENU = "fxml/Menu.fxml";
    private static final String PATH_TO_REGISTER = "fxml/Register.fxml";
    private DatabaseConnection databaseConnection;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void loginUser(ActionEvent event) {

        if (!areFieldsEmpty()) {
            String login = loginTextField.getText();
            String password = passwordField.getText();
            initializeDatabase();
            try {
                Optional<User> userOptional = databaseConnection.getUserParams(login, password);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    Statistics statistics = databaseConnection.getUserStatistics(login);
                    user.setStatistics(statistics);
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PATH_TO_MENU));
                    ControllersHelper.changeScene(loginButton, fxmlLoader);
                    MenuController menuController = fxmlLoader.getController();
                    menuController.setUser(user);
                } else
                    new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.WARNING, AlertHandler.LOGIN_WARNING, AlertHandler.USER_DOES_NOT_EXIST);
            } catch (SQLException e) {
                new AlertHandler().display(Alert.AlertType.ERROR, AlertHandler.ERROR, AlertHandler.DATABASE_ERROR, e.getMessage());
            }
        } else {
            new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.WARNING, AlertHandler.LOGIN_WARNING, AlertHandler.NO_LOGIN_OR_PASSWORD);
        }
        refreshLabels();
    }

    @FXML
    void registerUser(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PATH_TO_REGISTER));
        ControllersHelper.changeScene(registerButton, fxmlLoader);
    }


    private boolean areFieldsEmpty() {
        return loginTextField.getText().isEmpty() || passwordField.getText().isEmpty();
    }

    private void initializeDatabase() {
        try {
            databaseConnection = new DatabaseConnection();
        } catch (ClassNotFoundException e) {
            new AlertHandler().display(Alert.AlertType.ERROR, AlertHandler.ERROR, AlertHandler.DATABASE_ERROR, e.getMessage());
        }
    }

    private void refreshLabels() {
        loginTextField.clear();
        passwordField.clear();
    }

}
