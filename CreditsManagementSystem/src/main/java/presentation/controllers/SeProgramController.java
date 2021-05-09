package presentation.controllers;


import Intefaces.ICredit;
import domain.ICreditsManagementSystem;
import Intefaces.IProgram;
import domain.CreditsManagementSystem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import java.util.List;
import java.util.ResourceBundle;

public class SeProgramController implements Initializable {
    public Label programNavn;
    public Label programDato;
    public Label programGenre;
    public ImageView programImage;
    public ListView<ICredit> creditList;
    public Button backBt;

    public Button opretCreditBT;
    private IProgram iProgram;
    private ICreditsManagementSystem creditsManagementSystem;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        creditsManagementSystem = CreditsManagementSystem.getCreditManagementSystem();
        iProgram = creditsManagementSystem.getProgram();
        opretCreditBT.setVisible(false);
        seProgram();
        showBrugerOptions();


    }
    private void seProgram(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        programNavn.setText(iProgram.getProgramNavn());
        programDato.setText(simpleDateFormat.format(iProgram.getUdgivelsesDato()));
        programGenre.setText(iProgram.getGenre().toString());
        try {
            if(iProgram.getImagePath()!=null){
                String imagepath = String.valueOf(App.class.getResource("pictures/"+iProgram.getImagePath()).toURI().toURL());
                if(imagepath!=null){
                    programImage.setImage(new Image(imagepath));
                }
            }

        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
        showCredits();
    }



    public void backtoStartSideHandler() throws IOException {
        App.getStage().setScene(new Scene(loadFXML("startSide")));
    }


    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/"+fxml + ".fxml"));
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


    private void showCredits(){
        List<ICredit> credits = iProgram.getCredits();
        ObservableList<ICredit> observableList = FXCollections.observableList(credits);
        creditList.setItems(observableList);


    }
}
