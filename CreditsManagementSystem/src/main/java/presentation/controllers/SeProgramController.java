package presentation.controllers;


import Intefaces.ICredit;
import domain.ICreditsManagementSystem;
import Intefaces.IProgram;
import domain.CreditsManagementSystem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
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
import static presentation.App.loadFXML;


public class SeProgramController implements Initializable {
    public Label programNavn;
    public Label programDato;
    public Label programGenre;
    public ImageView programImage;
    public ListView<ICredit> creditList;
    public Button tilbage;

    public Button opretCreditBT;
    private IProgram program;
    private ICreditsManagementSystem creditsManagementSystem;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        creditsManagementSystem = CreditsManagementSystem.getCreditManagementSystem();
        program = creditsManagementSystem.getProgram();
        opretCreditBT.setVisible(false);
        seProgram();
        showBrugerOptions();


    }

    private void seProgram() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        programNavn.setText(program.getProgramNavn());
        programDato.setText(simpleDateFormat.format(program.getUdgivelsesDato()));
        programGenre.setText(program.getGenre().toString());
        try {
            if (program.getImagePath() != null) {
                String imagepath = String.valueOf(App.class.getResource("pictures/" + program.getImagePath()).toURI().toURL());
                if (imagepath != null) {
                    programImage.setImage(new Image(imagepath));
                }
            }

        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
        seCredits();
    }

    public void backtoStartSideHandler() throws IOException {
        App.getStage().setScene(new Scene(loadFXML("startSide")));
    }

    public void opretCreditHandler(ActionEvent actionEvent) {
        try {
            App.getStage().setScene(new Scene(loadFXML("opretCredit")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showBrugerOptions() {
        List<Integer> produktionsIDer = creditsManagementSystem.getBruger().getProduktionsIDer();
        if (produktionsIDer != null) {
            for (Integer produktionsID : produktionsIDer) {
                if (produktionsID == creditsManagementSystem.getProgram().getProduktionsID()) {
                    opretCreditBT.setVisible(true);
                }
            }
        }

        if (creditsManagementSystem.isAdmin()) {
            opretCreditBT.setVisible(true);
        }


    }


    private void seCredits() {
        List<ICredit> credits = program.getCredits();
        ObservableList<ICredit> observableList = FXCollections.observableList(credits);
        creditList.setItems(observableList);


    }
}
