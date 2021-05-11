package presentation.controllers;

import Intefaces.IRolle;
import domain.ICreditsManagementSystem;
import domain.CreditsManagementSystem;
import domain.credits.Rolle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import presentation.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static presentation.App.loadFXML;


public class CreditsController implements Initializable {
    public Button opretButton;
    public Button tilbage;
    public Label label;
    public TextField personID;
    public ComboBox rolletype;
    public TextArea beskrivelse;
    private ICreditsManagementSystem creditsManagementSystem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        creditsManagementSystem = CreditsManagementSystem.getCreditManagementSystem();

    }




    @FXML
   public void opretCreditHandler(ActionEvent actionEvent){
        if(personID.getText().equals("") || rolletype.getValue().equals("") || beskrivelse.getText().equals("")){
            label.setText("udfyld felterne");
        }
        else{
            String returnAnswer = creditsManagementSystem.opretCredit(String.valueOf(creditsManagementSystem.getProgram().getProduktionsID()),rolletype.getValue().toString(),personID.getText(),beskrivelse.getText());
            label.setText(returnAnswer);
        }

    }


    public void toStartScreen(ActionEvent event) {
        try {
            App.getStage().setScene(new Scene(loadFXML("startSide")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void seRoller(MouseEvent mouseEvent) {
        for (IRolle rolle: creditsManagementSystem.getRoller() ){
            rolletype.getItems().add(rolle);
        }


    }
}
