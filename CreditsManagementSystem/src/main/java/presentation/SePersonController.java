package presentation;


import Intefaces.*;

import domain.creditManagement.CreditsManagementSystem;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import static presentation.App.loadFXML;

public class SePersonController implements Initializable {
    public Label personID;
    public Label personnavnTxt;
    public Label fødselsdatoTxt;
    public Label nationalitetTxt;
    public ImageView personImage;
    public Text creditList;
    private ICreditsManagementSystem iCreditsManagementSystem;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iCreditsManagementSystem = CreditsManagementSystem.getCreditManagementSystem();
        IPerson person = iCreditsManagementSystem.getPerson();
        sePerson();

    }

    private void sePerson(){
        IPerson person = iCreditsManagementSystem.getPerson();
        personnavnTxt.setText(person.getNavn());
        personID.setText(String.valueOf(person.getPersonID()));
        nationalitetTxt.setText(person.getNationalitet());
        fødselsdatoTxt.setText(person.getFoedselsdato().toString());
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

        StringBuilder  stringBuilder = new StringBuilder();

        for (IProgram iProgram : iCreditsManagementSystem.getPrograms()) {
            for (ICredit iCredit : iProgram.getCredits()) {
                if (iCredit.getPerson().getPersonID() == person.getPersonID()) {
                    stringBuilder.append(iCredit);
                }
            }
        }

        creditList.setText("Credits: " + stringBuilder);

    }

    public void backtoStartSideHandler(ActionEvent event) throws IOException {
        App.getStage().setScene(new Scene(loadFXML("startSide")));
    }
}

