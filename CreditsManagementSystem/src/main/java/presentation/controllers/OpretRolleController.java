package presentation.controllers;

import domain.ICreditsManagementSystem;
import domain.CreditsManagementSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import presentation.App;
import static presentation.App.loadFXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OpretRolleController implements Initializable {


    public Button tilbage;
    public Button opretButton;
    public TextField rolletypefield;
    public Label label;
    public Label resultText;
    private ICreditsManagementSystem iCreditsManagementSystem;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iCreditsManagementSystem = CreditsManagementSystem.getCreditManagementSystem();

    }

    public void toPreviousScene(ActionEvent event) {
        try {
            App.getStage().setScene(new Scene(loadFXML("minSide")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void opretRolleHandler(ActionEvent event) {
        if (rolletypefield.getText().equals("")) {
            label.setText("udfyld felterne");
        } else {
            resultText.setText(iCreditsManagementSystem.opretRolle(rolletypefield.getText()));
        }
    }

}
