package presentation.controllers;

import Intefaces.IPerson;
import Intefaces.IRolle;
import domain.ICreditsManagementSystem;
import domain.CreditsManagementSystem;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import presentation.App;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import static presentation.App.loadFXML;


public class CreditsController implements Initializable {
    public Button opretBt;
    public Button tilbage;
    public Label resultText;
    public ComboBox<String> personNavn;
    public ComboBox<String> rolletype;
    public TextArea beskrivelse;
    private ICreditsManagementSystem creditsManagementSystem;
    private HashMap<String, Integer> personmap;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        creditsManagementSystem = CreditsManagementSystem.getCreditManagementSystem();

    }


    @FXML
    public void opretCreditHandler(ActionEvent actionEvent) {
        if (personNavn.getValue() == null || rolletype.getValue() == null) {
            resultText.setTextFill(Paint.valueOf("red"));
            resultText.setText("Udfyld Person og Rolletype");
        } else {
            resultText.setTextFill(Paint.valueOf("black"));
            String returnAnswer = creditsManagementSystem.opretCredit(String.valueOf(creditsManagementSystem.getProgram().getProduktionsID()), rolletype.getValue().toString(), personmap.get(personNavn.getValue()).toString(), beskrivelse.getText());
            resultText.setText(returnAnswer);
        }

    }


    public void backToSeProgramHandler(ActionEvent event) {
        try {
            App.getStage().setScene(new Scene(loadFXML("seProgram")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void seRoller(MouseEvent mouseEvent) {
        rolletype.setItems(FXCollections.observableList(new ArrayList<>()));
        for (IRolle rolle : creditsManagementSystem.getRoller()) {
            rolletype.getItems().add(rolle.getRolletype());
        }


    }

    public void sePersoner(MouseEvent mouseEvent) {
        personNavn.setItems(FXCollections.observableList(new ArrayList<>()));
        personmap = new HashMap<>();
        for (IPerson person : creditsManagementSystem.getPersons()) {
            personNavn.getItems().add(person.getNavn());
            personmap.put(person.getNavn(), person.getPersonID());
        }
    }
}
