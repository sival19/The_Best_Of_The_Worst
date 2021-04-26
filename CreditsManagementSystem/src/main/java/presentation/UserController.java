package presentation;

import Intefaces.ICreditsManagementSystem;
import Intefaces.IHub;
import hub.Hub;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;

import static presentation.App.loadFXML;

public class UserController {

    public Button backToStart;
    public Button opretBt;
    public TextField username, password, eMail;
    public RadioButton admin, producer;
    private ICreditsManagementSystem creditsManagementSystem;
    ToggleGroup toggleGroup;
    private String rights;
    private IHub hub;

    public void initialize(){
        hub = new Hub();
        toggleGroup = new ToggleGroup();
        admin.setToggleGroup(toggleGroup);
        producer.setToggleGroup(toggleGroup);
        creditsManagementSystem = hub.getCreditManagementSystem();

    }

    public String opretBruger(String brugernavn, String adgangskode, String email, String rettighed) {
        return creditsManagementSystem.opretBruger(brugernavn, adgangskode, email, rettighed);
    }

    public void toStartScreen(ActionEvent event) {
        try {
            App.getStage().setScene(new Scene(loadFXML("startSide")));
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
        System.out.println(opretBruger(username.getText(), password.getText(), eMail.getText(), rights));
    }
}
