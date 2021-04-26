package presentation;

import Intefaces.ICreditsManagementSystem;
import Intefaces.IHub;
import hub.Hub;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

import static presentation.App.loadFXML;

public class OpretPersonController {

    public Button tilbage;
    public Button opretButton;
    public Label label;
    public TextField navn;
    public TextField nationalitet;
    public TextField yearField;
    public TextField monthField;
    public TextField dayField;
    private ICreditsManagementSystem creditsManagementSystem;
    private IHub hub;

    public void toPreviousScene(ActionEvent event) {
        try {
            App.getStage().setScene(new Scene(loadFXML("minSide")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize(){
        hub = new Hub();
        creditsManagementSystem = hub.getCreditManagementSystem();
    }

    @FXML
    public void opretPersonHandler(ActionEvent event){
        if(navn.getText().equals("")|| nationalitet.getText().equals("")||yearField.getText().equals("") || monthField.getText().equals("") || dayField.getText().equals("")){
            label.setText("udfyld felterne");
        } else{
            String returnAnswer = creditsManagementSystem.opretPerson(navn.getText(), nationalitet.getText(),   yearField.getText() + "-" + monthField.getText() + "-" +dayField.getText());
        }
    }



}
