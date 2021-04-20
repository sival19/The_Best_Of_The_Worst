package presentation;

import Factory.CreditManagementSystemFactory;
import Intefaces.ICreditsManagementSystem;
import domain.logIn.Rettighed;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;

import static presentation.App.loadFXML;

public class UserController {

    public Button backToStart;
    public Button opretBt;
    public TextField username, password, eMail;
    public RadioButton admin, producer;
    ICreditsManagementSystem creditsManagementSystem;


    public void initialize(){
        creditsManagementSystem = CreditManagementSystemFactory.getCreditManagementSystem();
        if(creditsManagementSystem.isAdmin()){
            //TODO SHOW ADMINISTRATOR OPTIONS ON SCREEN
        }
        else{
            //TODO HIDE ADMINISTRATOR OPTIONS ON SCREEN
        }
    }
    public String opretBruger(String brugernavn, String adgangskode, String email, String rettighed){
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
        String rights;
        //eventuelt lave rettighed om til Enum f eks: a = Rettighed.ADMINISTRATOR
        if(event.getSource()==admin){
            rights = "Administrator";
            producer.setDisable(true);
        } else {
            rights = "Producer";
            admin.setDisable(true);
        }
        opretBruger(username.getText(), password.getText(), eMail.getText(), rights);
       //TODO IMPLEMENTER OPRETBRUGER MED GUI DATA opretBruger("helle","holle","hello","Administrator");
    }


}
