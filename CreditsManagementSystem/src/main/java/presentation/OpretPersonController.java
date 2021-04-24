package presentation;

import Factory.CreditManagementSystemFactory;
import Intefaces.ICreditsManagementSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Date;

import static presentation.App.loadFXML;

public class OpretPersonController {

    public Button tilbage;
    public Button opretButton;
    public Label label;
    public TextField navn;
    public TextField personID;
    public TextField nationalitet;
    public TextField fødselsdato;
    ICreditsManagementSystem creditsManagementSystem;

    public void toPreviousScene(ActionEvent event) {
        try {
            App.getStage().setScene(new Scene(loadFXML("minSide")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize(){
        creditsManagementSystem = CreditManagementSystemFactory.getCreditManagementSystem();
    }

    @FXML
    void opretPersonHandler(ActionEvent event){
        if(navn.getText().equals("")|| nationalitet.getText().equals("")||fødselsdato.getText().equals("")){
            label.setText("udfyld felterne");
        } else{
            String returnAnswer = creditsManagementSystem.opretPerson(navn.getText(), nationalitet.getText(), fødselsdato.getText());
        }
    }



}
