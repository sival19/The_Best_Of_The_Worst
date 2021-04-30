package presentation;


import Intefaces.*;

import hub.Hub;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import static presentation.App.loadFXML;

public class SeRolleController implements Initializable {
    public Label rolleNavnTxt;
    public ImageView rolleImage;
    public Text creditList;
    private ICreditsManagementSystem iCreditsManagementSystem;
    private IHub hub;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hub = new Hub();
        iCreditsManagementSystem = hub.getCreditManagementSystem();
        seRolle();

    }


    private void seRolle(){
        IRolle rolle = iCreditsManagementSystem.getRolle();
        rolleNavnTxt.setText(rolle.getRolletype());

        if(rolleImage.getImage()!=null){
            try {
                rolleImage.setImage(new Image(SeRolleController.class.getResource(rolle.getImagePath()).toURI().toString()));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

        }

        StringBuilder stringBuilder = new StringBuilder();
        for (IProgram iProgram : iCreditsManagementSystem.getPrograms()) {
            for (ICredit iCredit : iProgram.getCredits()) {
                if (iCredit.getRolle().getRolletype().equalsIgnoreCase(rolle.getRolletype())) {
                    stringBuilder.append(iCredit);
                }
            }
        }


        creditList.setText("Credits: :"+ stringBuilder);


    }


    public void backtoStartSideHandler(ActionEvent event) throws IOException {
        App.getStage().setScene(new Scene(loadFXML("startSide")));
    }
}

