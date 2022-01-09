package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllersHelper {

    private static final String LOGIN = "Login";
    private static final String PASSWORD = "Hasło";
    private static final String DIFFICULTY_LEVEL = "Poziom trudności";
    private static final String EASY = "Łatwy";
    private static final String MEDIUM = "Średni";
    private static final String HARD = "Trudny";
    private static final String LABEL_STYLE = "-fx-font-weight: bold;" +
            "-fx-text-fill: #fff0b3;" +
            "-fx-font-size: 20;" +
            "-fx-alignment: center";

    public static void changeScene(Button button, FXMLLoader fxmlLoader){
        try {
            Parent root = fxmlLoader.load();
            Stage window = (Stage) (button.getScene().getWindow());
            window.setScene(new Scene(root, 800, 600));
            window.show();
        }catch (IOException e){
            new AlertHandler().display(Alert.AlertType.ERROR, AlertHandler.ERROR, AlertHandler.IO_EXPECTATION, e.getMessage());
        }
    }

    public static void changeSceneToMap(Button button, FXMLLoader fxmlLoader){
        try {
            Parent root = fxmlLoader.load();
            Stage window = (Stage) (button.getScene().getWindow());
            window.setScene(new Scene(root, 1200, 800));
            window.show();
        }catch (IOException e){
            //new AlertHandler().display(Alert.AlertType.ERROR, AlertHandler.ERROR, AlertHandler.IO_EXPECTATION, e.getMessage());
           e.printStackTrace();
        }
    }

    public static TextField initLoginTextField() {
        TextField textField = new TextField();
        textField.setPromptText(LOGIN);
        textField.setPrefSize(150, 25);
        textField.setLayoutX(75);
        textField.setLayoutY(30);
        return textField;
    }

    public static PasswordField initPasswordField() {
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText(PASSWORD);
        passwordField.setPrefSize(150, 25);
        passwordField.setLayoutX(75);
        passwordField.setLayoutY(68);
        return passwordField;
    }

    public static ComboBox<String> initCombobox() {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setPromptText(DIFFICULTY_LEVEL);
        comboBox.setPrefSize(150, 25);
        comboBox.setLayoutX(75);
        comboBox.setLayoutY(68);
        comboBox.getItems().addAll(EASY, MEDIUM, HARD);
        return comboBox;
    }

    public static ComboBox<String> initCombobox(int layoutX, int layoutY) {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setPromptText(DIFFICULTY_LEVEL);
        comboBox.setPrefSize(150, 25);
        comboBox.setLayoutX(layoutX);
        comboBox.setLayoutY(layoutY);
        comboBox.getItems().addAll(EASY, MEDIUM, HARD);
        return comboBox;
    }

    public static Label initLabel(String login){
        Label label = new Label();
        label.setText(login);
        label.setLayoutX(0);
        label.setLayoutY(65);
        label.setPrefSize(300,30);
        label.styleProperty().setValue(LABEL_STYLE);
        return label;
    }

    public static Label initLabel(String login, int layoutX, int layoutY, int prefWidth, int prefHeight){
        Label label = new Label();
        label.setText(login);
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);
        label.setPrefSize(prefWidth,prefHeight);
        label.styleProperty().setValue(LABEL_STYLE);
        return label;
    }

}
