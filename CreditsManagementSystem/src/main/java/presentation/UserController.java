package presentation;

import Factory.CreditManagementSystemFactory;
import Intefaces.ICreditsManagementSystem;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

import static presentation.App.loadFXML;

public class UserController {

    public Button backToStart;
    public Button opretBt;
    ICreditsManagementSystem creditsManagementSystem;


    public void initialize() {
        creditsManagementSystem = CreditManagementSystemFactory.getCreditManagementSystem();
        if (creditsManagementSystem.isAdmin()) {
            //TODO SHOW ADMINISTRATOR OPTIONS ON SCREEN
        } else {
            //TODO HIDE ADMINISTRATOR OPTIONS ON SCREEN
        }
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

    public void brugerHandler(ActionEvent a) {
        //TODO IMPLEMENTER OPRETBRUGER MED GUI DATA opretBruger("helle","holle","hello","Administrator");
    }
}
