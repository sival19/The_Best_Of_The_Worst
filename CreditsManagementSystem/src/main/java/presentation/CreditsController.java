package presentation;

import domain.ICreditsManagementSystem;
import domain.CreditsManagementSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static presentation.App.loadFXML;


public class CreditsController implements Initializable {
    public Button opretButton;
    public Button tilbage;
    public Label label;
    public TextField personID;
    public TextField rolletype;
    public TextArea beskrivelse;
    private ICreditsManagementSystem creditsManagementSystem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        creditsManagementSystem = CreditsManagementSystem.getCreditManagementSystem();

    }


    @FXML
   public void opretCreditHandler(ActionEvent actionEvent){
        if(personID.getText().equals("") || rolletype.getText().equals("") || beskrivelse.getText().equals("")){
            label.setText("udfyld felterne");
        }
        else{
            String returnAnswer = creditsManagementSystem.opretCredit(String.valueOf(creditsManagementSystem.getProgram().getProduktionsID()),rolletype.getText(),personID.getText(),beskrivelse.getText());
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
}
