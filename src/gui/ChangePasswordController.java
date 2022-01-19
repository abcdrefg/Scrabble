package gui;

import User.User;
import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

import java.sql.SQLException;

public class ChangePasswordController {

    private static final String PATH_TO_MENU = "fxml/Menu.fxml";
    private User user;
    @FXML
    private Button changePasswordButton;

    @FXML
    private Button returnButton;

    @FXML
    private PasswordField recentPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField repeatPasswordField;

    private DatabaseConnection databaseConnection;

    @FXML
    void changePassword(ActionEvent event) {
        initDatabaseConnection();
        if (recentPasswordField.getText().isEmpty() || newPasswordField.getText().isEmpty() || repeatPasswordField.getText().isEmpty()) {
            new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.ERROR, "Wypełnij wszystkie pola!", "Nie wszystkie pola zostały uzupełnione!");
        } else {
            String newPassword = newPasswordField.getText();
            String repeatedNewPassword = repeatPasswordField.getText();
            String recentPassword = recentPasswordField.getText();
            if (newPassword.equals(repeatedNewPassword)) {
                if (!recentPassword.equals(newPassword)) {
                    String login = user.getLogin();
                    String providedRecentPassword = recentPasswordField.getText();
                    try {
                        if (databaseConnection.changePassword(login, providedRecentPassword, newPassword)) {
                            new AlertHandler().display(Alert.AlertType.CONFIRMATION, AlertHandler.CONFIRMATION, "Zmiana hasła zakończona pomyślnie!", "Zmiana hasła zakończona pomyślnie!");
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Menu.fxml"));
                            ControllersHelper.changeScene(changePasswordButton, fxmlLoader);
                        } else {
                            new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.WARNING, "Błąd podczas zmiany hasła!", "Błąd podczas zmiany hasła!");
                            refreshPasswordFields();
                        }
                    } catch (SQLException e) {
                        new AlertHandler().display(Alert.AlertType.ERROR, AlertHandler.ERROR, AlertHandler.DATABASE_ERROR, e.getMessage());
                        refreshPasswordFields();
                    }
                } else {
                    new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.WARNING, "Błąd podczas zmiany hasła!", "Nowe hasło musi być inne od aktualnego!");
                }
            } else {
                new AlertHandler().display(Alert.AlertType.WARNING, AlertHandler.WARNING, "Błąd podczas zmiany hasła!", "Nowe hasło nie zostało poprawnie powtórzone ");
            }
        }
    }

    @FXML
    void returnToMenuPage(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PATH_TO_MENU));
        ControllersHelper.changeScene(returnButton, fxmlLoader);
    }

    private void initDatabaseConnection() {
        try {
            databaseConnection = new DatabaseConnection();
        } catch (ClassNotFoundException e) {
            new AlertHandler().display(Alert.AlertType.ERROR, AlertHandler.ERROR, AlertHandler.DATABASE_ERROR, AlertHandler.DATABASE_ERROR);
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void refreshPasswordFields() {
        repeatPasswordField.setText("");
        newPasswordField.setText("");
        recentPasswordField.setText("");
    }

}

