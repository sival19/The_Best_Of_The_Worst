package presentation;

import Factory.CreditManagementSystemFactory;
import Intefaces.*;
import domain.creditManagement.Catalog;
import domain.creditManagement.CatalogObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextFlow;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;


public class CreditsController implements Initializable {
    public Button opretButton;
    public Label label;
    public TextField personID;
    public TextField rolletype;
    public TextField produktionsID;
    public TextArea beskrivelse;

    private IDataPerson iDataPerson;
    private IDataRolle iDataRolle;

    ICreditsManagementSystem creditsManagementSystem;


    @FXML
    void opretCreditHandler(ActionEvent actionEvent){
        if(personID.getText().equals("")|| produktionsID.getText().equals("") || rolletype.getText().equals("") || beskrivelse.getText().equals("")){
            label.setText("udfyld felterne");
        }
        else{
            String returnAnswer = creditsManagementSystem.opretCredit(produktionsID.getText(),rolletype.getText(),personID.getText(),beskrivelse.getText());
            label.setText(returnAnswer);
        }

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        creditsManagementSystem = CreditManagementSystemFactory.getCreditManagementSystem();

    }

    private void seProgram(){


    }

}
