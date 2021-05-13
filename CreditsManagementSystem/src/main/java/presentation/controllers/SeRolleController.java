package presentation.controllers;


import Intefaces.ICredit;
import domain.ICreditsManagementSystem;
import Intefaces.IProgram;
import Intefaces.IRolle;
import domain.CreditsManagementSystem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import presentation.App;

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
    public ListView<ICredit> creditList;
    private ICreditsManagementSystem iCreditsManagementSystem;
    private IRolle rolle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iCreditsManagementSystem = CreditsManagementSystem.getCreditManagementSystem();
        rolle = iCreditsManagementSystem.getRolle();
        seRolle();

    }


    private void seRolle(){
        rolleNavnTxt.setText(rolle.getRolletype());

        if(rolleImage.getImage()!=null){
            try {
                rolleImage.setImage(new Image(App.class.getResource("pictures/"+rolle.getImagePath()).toURI().toString()));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

        }
        seCredit();

    }


    public void backtoStartSideHandler(ActionEvent event) throws IOException {
        App.getStage().setScene(new Scene(loadFXML("startSide")));
    }

    void seCredit(){
        List<ICredit> credits = new ArrayList<>();
        for (IProgram iProgram : iCreditsManagementSystem.getPrograms()) {
            for (ICredit iCredit : iProgram.getCredits()) {
                if (iCredit.getRolle().getRolletype().equalsIgnoreCase(rolle.getRolletype())) {
                    credits.add(iCredit);
                }
            }
        }


        ObservableList<ICredit> creditObservableList = FXCollections.observableList(credits);
        creditList.setItems(creditObservableList);


    }
}

