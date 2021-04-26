package presentation;

import Intefaces.ICreditsManagementSystem;
import Intefaces.IDataBruger;
import Intefaces.IHub;
import hub.Hub;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static presentation.App.loadFXML;

public class MinSideController implements Initializable {
    public Label brugerIDTxt;
    public Label brugerNavnTxt;
    public Label adgangskodeTxt;
    public Label emailTxt;
    public Label rettighedTxt;
    public TextArea produktioner;
    public Button opretProgramBT;
    public Button opretBrugerBT;
    public Button opretPersonBT;
    public Button opretRolleBT;
    private ICreditsManagementSystem iCreditsManagementSystem;
    private IDataBruger iDataBruger;
    private IHub hub;

    public void toStartScreen(ActionEvent actionEvent) {
        try{
            App.getStage().setScene(new Scene(loadFXML("startSide")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hub = new Hub();
        iCreditsManagementSystem = hub.getCreditManagementSystem();
        iDataBruger = iCreditsManagementSystem.getBruger();
        brugerIDTxt.setText(String.valueOf(iDataBruger.getBrugerID()));
        brugerNavnTxt.setText(iDataBruger.getBrugernavn());
        adgangskodeTxt.setText(iDataBruger.getAdgangskode());
        emailTxt.setText(iDataBruger.getEmail());
        rettighedTxt.setText(iDataBruger.getRettighed().toString());
        showBrugerOptions();
    }


    private void showBrugerOptions(){

        if(iDataBruger.getRettighed().toString().equalsIgnoreCase("Producer")){
            opretBrugerBT.setVisible(false);
            opretProgramBT.setVisible(true);
            produktioner.setVisible(true);
            opretPersonBT.setVisible(true);
            opretRolleBT.setVisible(true);

        }
        else if(iDataBruger.getRettighed().toString().equalsIgnoreCase("Administrator")){
            opretPersonBT.setVisible(false);
            opretRolleBT.setVisible(false);
            opretProgramBT.setVisible(false);
            produktioner.setVisible(false);
            opretBrugerBT.setVisible(true);
        }
        else {
            opretPersonBT.setVisible(false);
            opretRolleBT.setVisible(false);
            opretProgramBT.setVisible(false);
            produktioner.setVisible(false);
            opretBrugerBT.setVisible(false);
        }
    }

    public void opretHandler(ActionEvent actionEvent) {
        if(actionEvent.getSource()==opretBrugerBT){
            try {
                App.getStage().setScene(new Scene(loadFXML("opretBruger")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(actionEvent.getSource()==opretPersonBT){
            try {
                App.getStage().setScene(new Scene(loadFXML("opretPerson")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(actionEvent.getSource()==opretProgramBT){
            try {
                App.getStage().setScene(new Scene(loadFXML("opretProgram")));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(actionEvent.getSource()==opretRolleBT){
            try {
                App.getStage().setScene(new Scene(loadFXML("opretRolle")));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
