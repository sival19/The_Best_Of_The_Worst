package presentation.controllers;

import domain.ICreditsManagementSystem;
import domain.CreditsManagementSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import presentation.App;

import java.io.IOException;

import static presentation.App.loadFXML;

public class OpretPersonController {

    public Button tilbage;
    public Button opretButton;
    public TextField navn;
    public TextField nationalitet;
    public TextField yearField;
    public TextField monthField;
    public TextField dayField;
    public Label resultLbl;
    private ICreditsManagementSystem creditsManagementSystem;

    public void toPreviousScene(ActionEvent event) {
        try {
            App.getStage().setScene(new Scene(loadFXML("minSide")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize(){
        creditsManagementSystem = CreditsManagementSystem.getCreditManagementSystem();
    }

    @FXML
    public void opretPersonHandler(ActionEvent event){
        if(navn.getText().equals("")|| nationalitet.getText().equals("")||yearField.getText().equals("") || monthField.getText().equals("") || dayField.getText().equals("")){
            resultLbl.setText("udfyld felterne");
        } else{
            resultLbl.setText(creditsManagementSystem.opretPerson(navn.getText(), nationalitet.getText(),   yearField.getText() + "-" + monthField.getText() + "-" +dayField.getText()));
        }
    }



}
