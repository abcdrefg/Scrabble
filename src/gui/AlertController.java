package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AlertController {

    @FXML
    public Button confirmationButton;

    @FXML
    public Label errorMessageLabel;

    @FXML
    void closeWindow(ActionEvent event) {

    }

    public void setErrorMessageLabelText(String error){
        errorMessageLabel.setText(error);
    }

    public void setConfirmationButtonAction(Stage stage){
        confirmationButton.setOnAction(e -> stage.close());
    }

}
