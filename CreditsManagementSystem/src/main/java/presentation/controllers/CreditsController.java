package presentation.controllers;

import Intefaces.IPerson;
import Intefaces.IRolle;
import domain.ICreditsManagementSystem;
import domain.CreditsManagementSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import presentation.App;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import static presentation.App.loadFXML;


public class CreditsController implements Initializable {
    public Button opretButton;
    public Button tilbage;
    public Label label;
    public ComboBox<String> personID;
    public ComboBox<String> rolletype;
    public TextArea beskrivelse;
    private ICreditsManagementSystem creditsManagementSystem;
    private HashMap<String, Integer> personmap;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        creditsManagementSystem = CreditsManagementSystem.getCreditManagementSystem();

    }




    @FXML
   public void opretCreditHandler(ActionEvent actionEvent){
        if(personID.getValue().equals("") || rolletype.getValue().equals("") || beskrivelse.getText().equals("")){
            label.setText("udfyld felterne");
        }
        else{
            String returnAnswer = creditsManagementSystem.opretCredit(String.valueOf(creditsManagementSystem.getProgram().getProduktionsID()),rolletype.getValue().toString(), personmap.get(personID.getValue()).toString(),beskrivelse.getText());
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
            rolletype.getItems().add(rolle.getRolletype());
        }


    }

    public void sePersoner(MouseEvent mouseEvent) {
        personmap = new HashMap<>();

        for (IPerson person: creditsManagementSystem.getPersons() ){
            personID.getItems().add(person.getNavn());
            personmap.put(person.getNavn(), person.getPersonID());
        }
    }
}
