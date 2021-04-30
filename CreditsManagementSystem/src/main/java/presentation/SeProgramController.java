package presentation;


import Intefaces.ICreditsManagementSystem;
import Intefaces.IProgram;
import Intefaces.IHub;
import hub.Hub;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.text.Text;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SeProgramController implements Initializable {
    public Label programNavn;
    public Label programDato;
    public Label programGenre;
    public ImageView programImage;
    public Text creditList;
    public Button backBt;

    public Button opretCreditBT;
    private IProgram iProgram;
    private ICreditsManagementSystem creditsManagementSystem;
    private IHub hub;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hub = new Hub();
        creditsManagementSystem = hub.getCreditManagementSystem();
        iProgram = creditsManagementSystem.getProgram();
        opretCreditBT.setVisible(false);
        seProgram();
        showBrugerOptions();
    }
    private void seProgram(){
        programNavn.setText(iProgram.getProgramNavn());
        programDato.setText(iProgram.getUdgivelsesDato().toString());
        programGenre.setText(iProgram.getGenre().toString());
        try {
            if(iProgram.getImagePath()!=null){
                String imagepath = String.valueOf(StartSideController.class.getResource(iProgram.getImagePath()).toURI().toURL());
                if(imagepath!=null){
                    programImage.setImage(new Image(imagepath));
                }
            }

        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
        creditList.setText("Credits: " + iProgram.getCreditListString());

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

    public void showBrugerOptions(){
        List<Integer> produktionsIDer = creditsManagementSystem.getBruger().getProduktionsIDer();
        System.out.println(produktionsIDer);
        if(produktionsIDer!= null){
            for(Integer produktionsID: produktionsIDer){
                if(produktionsID == creditsManagementSystem.getProgram().getProduktionsID()){
                    opretCreditBT.setVisible(true);
                }
            }
        }

        if(creditsManagementSystem.isAdmin()){
            opretCreditBT.setVisible(true);
        }


    }
}
