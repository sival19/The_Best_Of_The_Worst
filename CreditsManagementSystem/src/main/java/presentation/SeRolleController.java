package presentation;


import Factory.CreditManagementSystemFactory;
import Intefaces.ICreditsManagementSystem;
import Intefaces.IDataCredit;
import Intefaces.IDataProgram;
import Intefaces.IDataRolle;
import domain.creditManagement.CreditsManagementSystem;
import domain.credits.Rolle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import static presentation.App.loadFXML;

public class SeRolleController implements Initializable {
    public Label rolleNavnTxt;
    public ImageView rolleImage;
    public Text creditList;
    ICreditsManagementSystem iCreditsManagementSystem;
    List<IDataCredit> iDataCredits;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iCreditsManagementSystem = CreditManagementSystemFactory.getCreditManagementSystem();
        IDataRolle rolle = iCreditsManagementSystem.getRolle();
        iDataCredits = new ArrayList<>();
        rolleNavnTxt.setText(rolle.getRolletype());
        for (IDataProgram iDataProgram: iCreditsManagementSystem.getPrograms()){
           for (IDataCredit iDataCredit : iDataProgram.getCredits()){
               if(iDataCredit.getRolle().getRolletype().equalsIgnoreCase(rolle.getRolletype())){
                   iDataCredits.add(iDataCredit);
               }
           }
        }

        creditList.setText(iDataCredits.toString());
        try {
            rolleImage.setImage(new Image(SeRolleController.class.getResource(rolle.getImagePath()).toURI().toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void backtoStartSideHandler(ActionEvent event) throws IOException {
        App.getStage().setScene(new Scene(loadFXML("startSide")));
    }
}

