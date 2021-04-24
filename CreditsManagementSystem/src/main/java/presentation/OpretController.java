package presentation;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

import static presentation.App.loadFXML;

public class OpretController {

    public Button tilbage;
    public Button opretCredit;
    public Button opretPerson;

    public void toStartScreen(ActionEvent event) {
        try {
            App.getStage().setScene(new Scene(loadFXML("startSide")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void opretCreditHandler(ActionEvent event) {
        try{
            App.getStage().setScene(new Scene(loadFXML("opretCredit")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void opretPersonHandler(ActionEvent event){
        try{
            App.getStage().setScene(new Scene(loadFXML("opretPerson")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }


}
