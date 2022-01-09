package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static final String PATH_TO_LOGIN = "fxml/Login.fxml";
    private static final String SCRABBLE = "Scrabble";

    public static void main(String[] args) {
        launch(args);
    }

    @Override

    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PATH_TO_LOGIN));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle(SCRABBLE);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
