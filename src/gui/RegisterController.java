package gui;

import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {
    private static final String PATH_TO_LOGIN = "fxml/Login.fxml";
    private DatabaseConnection databaseConnection;
    @FXML
    private Button registerButton;

    @FXML
    private Button returnButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField repeatPasswordField;

    @FXML
    void registerNewUser(ActionEvent event) {
        if (!checkIfFieldsAreEmpty() && checkIfPasswordsEquals()) {
            try {
                String login = loginField.getText();
                String password = passwordField.getText();
                initializeDatabase();
                if (databaseConnection.createNewUser(login, password)) {
                    new AlertHandler().display(Alert.AlertType.CONFIRMATION, AlertHandler.CONFIRMATION, AlertHandler.SUCCESSFUL_REGISTRATION, "Użytkownik " + login + " został zarejestrowany");
                }
            } catch (SQLException e) {
                new AlertHandler().display(Alert.AlertType.ERROR, AlertHandler.ERROR, AlertHandler.DATABASE_ERROR, e.getMessage());
            }
        } else {
            new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.WARNING, AlertHandler.REGISTRATION_ERROR, AlertHandler.NOT_IDENTICAL_PASSWORDS_OR_EMPTY_FIELDS);
        }
        refreshLabels();
    }

    @FXML
    void returnToLoginPage(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PATH_TO_LOGIN));
        ControllersHelper.changeScene(returnButton, fxmlLoader);
    }

    private void initializeDatabase() {
        try {
            databaseConnection = new DatabaseConnection();
        } catch (ClassNotFoundException e) {
            new AlertHandler().display(Alert.AlertType.ERROR, AlertHandler.ERROR, AlertHandler.DATABASE_ERROR, e.getMessage());
        }
    }

    private void refreshLabels() {
        loginField.clear();
        passwordField.clear();
        repeatPasswordField.clear();
    }

    private boolean checkIfFieldsAreEmpty() {
        return loginField.getText().isEmpty() || passwordField.getText().isEmpty() || repeatPasswordField.getText().isEmpty();
    }

    private boolean checkIfPasswordsEquals() {
        return passwordField.getText().equals(repeatPasswordField.getText());
    }


}
