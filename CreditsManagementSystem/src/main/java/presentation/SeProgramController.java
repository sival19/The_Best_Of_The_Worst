package presentation;


import Factory.CreditManagementSystemFactory;
import Intefaces.ICreditsManagementSystem;
import Intefaces.IDataBruger;
import Intefaces.IDataProgram;
import domain.creditManagement.CreditsManagementSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class SeProgramController implements Initializable {
    public Label programNavn;
    public Label programDato;
    public Label programGenre;
    public ImageView programImage;
    public Text creditList;
    public Button backBt;

    public Button opretCreditBT;
    private IDataProgram iDataProgram;
    private IDataBruger iDataBruger;
    ICreditsManagementSystem creditsManagementSystem;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        creditsManagementSystem = CreditManagementSystemFactory.getCreditManagementSystem();
        iDataBruger = creditsManagementSystem.getBruger();
        iDataProgram = creditsManagementSystem.getProgram();
        seProgram();


    }
    private void seProgram(){
        programNavn.setText(iDataProgram.getProgramNavn());
        programDato.setText(iDataProgram.getUdgivelsesDato().toString());
        programGenre.setText(iDataProgram.getGenre().toString());
        try {
            programImage.setImage(new Image(String.valueOf(StartSideController.class.getResource(iDataProgram.getImagePath()).toURI().toURL())));
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
        creditList.setText(iDataProgram.getCreditListString());

    }





    public void backtoStartSideHandler() throws IOException {
        App.getStage().setScene(new Scene(loadFXML("startSide")));
    }


    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartSideController.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public void opretCreditHandler(ActionEvent actionEvent) {
        try {
            App.getStage().setScene(new Scene(loadFXML("opretCredit")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
