package presentation.controllers;


import Intefaces.ICredit;
import domain.ICreditsManagementSystem;
import Intefaces.IPerson;
import Intefaces.IProgram;
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
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static presentation.App.loadFXML;

public class SePersonController implements Initializable {
    public Label personID;
    public Label personnavnTxt;
    public Label fødselsdatoTxt;
    public Label nationalitetTxt;
    public ImageView personImage;
    public ListView<ICredit> creditList;
    private ICreditsManagementSystem iCreditsManagementSystem;
    private IPerson person;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iCreditsManagementSystem = CreditsManagementSystem.getCreditManagementSystem();
        person = iCreditsManagementSystem.getPerson();
        sePerson();

    }

    private void sePerson(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        personnavnTxt.setText(person.getNavn());
        personID.setText(String.valueOf(person.getPersonID()));
        nationalitetTxt.setText(person.getNationalitet());
        fødselsdatoTxt.setText(simpleDateFormat.format(person.getFoedselsdato()));
        try {
            if(person.getImagePath()!=null){
                String imagepath = String.valueOf(StartSideController.class.getResource(person.getImagePath()).toURI().toURL());
                if(imagepath!=null){
                    personImage.setImage(new Image(imagepath));
                }
            }

        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
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
                if (iCredit.getPerson().getPersonID() == person.getPersonID()) {
                    credits.add(iCredit);
                }
            }
        }
        ObservableList<ICredit> creditObservableList = FXCollections.observableList(credits);
        creditList.setItems(creditObservableList);


    }
}

