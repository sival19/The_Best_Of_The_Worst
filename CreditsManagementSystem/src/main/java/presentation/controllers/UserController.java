package presentation.controllers;

import domain.ICreditsManagementSystem;
import domain.CreditsManagementSystem;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import presentation.App;

import java.io.IOException;

import static presentation.App.loadFXML;

public class UserController {
    //Der er ændring her

    public Button backToStart;
    public Button opretBt;
    public TextField username, password, eMail;
    public RadioButton admin, producer;
    public Label resultLbl;
    private ICreditsManagementSystem creditsManagementSystem;
    private ToggleGroup toggleGroup;
    private String rights;

    public void initialize(){
        toggleGroup = new ToggleGroup();
        admin.setToggleGroup(toggleGroup);
        producer.setToggleGroup(toggleGroup);
        creditsManagementSystem = CreditsManagementSystem.getCreditManagementSystem();
        if(creditsManagementSystem.getBrugere().size()==0){
            producer.setDisable(true);
        }

    }

    public String opretBruger(String brugernavn, String adgangskode, String email, String rettighed) {
        return creditsManagementSystem.opretBruger(brugernavn, adgangskode, email, rettighed);
    }

    public void backScreen(ActionEvent event) {
        try {
            if (creditsManagementSystem.getBruger().getRettighed().toString().equals("Seer")){
                App.getStage().setScene(new Scene(loadFXML("startSide")));

            }
            else App.getStage().setScene(new Scene(loadFXML("minSide")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void brugerHandler(ActionEvent event){
        rights = "";

        if(admin.isSelected()){
            rights = "Administrator";
        } else if(producer.isSelected())  {
            rights = "Producer";
        }
        resultLbl.setText(opretBruger(username.getText(), password.getText(), eMail.getText(), rights));
    }
}