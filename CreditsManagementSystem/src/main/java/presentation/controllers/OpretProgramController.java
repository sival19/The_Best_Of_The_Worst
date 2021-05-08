package presentation.controllers;

import domain.ICreditsManagementSystem;
import domain.CreditsManagementSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import presentation.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OpretProgramController implements Initializable {
    public Button opretBt;

    public RadioButton dokumentarRdBt, filmRdBt, kortFilmRdBt, serieRdBt;
    public RadioButton actionRdBt, adventureRdBt,comedyRdBt, crimeRdBt,
            horrorRdBt, romanceRdBt, scifiRdBt, dramaRdBt, fantasyRdBt, thrillerRdBt;

    public ToggleGroup toggleGroupProgram;
    public ToggleGroup toggleGroupGenre;

    public Button backToStart;
    public TextField programnavnField;
    public TextField monthField, yrField, lengthField;

    private ICreditsManagementSystem creditsManagementSystem;
    public Label resultatTxt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        creditsManagementSystem = CreditsManagementSystem.getCreditManagementSystem();
        toggleGroupProgram = new ToggleGroup();
        toggleGroupGenre = new ToggleGroup();
        dokumentarRdBt.setToggleGroup(toggleGroupProgram); filmRdBt.setToggleGroup(toggleGroupProgram);
        kortFilmRdBt.setToggleGroup(toggleGroupProgram); serieRdBt.setToggleGroup(toggleGroupProgram);
        dokumentarRdBt.setUserData("Dokumentar"); filmRdBt.setUserData("Film");
        kortFilmRdBt.setUserData("KortFilm"); serieRdBt.setUserData("Serie");

        actionRdBt.setToggleGroup(toggleGroupGenre); adventureRdBt.setToggleGroup(toggleGroupGenre);
        comedyRdBt.setToggleGroup(toggleGroupGenre); crimeRdBt.setToggleGroup(toggleGroupGenre);
        horrorRdBt.setToggleGroup(toggleGroupGenre); romanceRdBt.setToggleGroup(toggleGroupGenre);
        scifiRdBt.setToggleGroup(toggleGroupGenre); dramaRdBt.setToggleGroup(toggleGroupGenre);
        fantasyRdBt.setToggleGroup(toggleGroupGenre); thrillerRdBt.setToggleGroup(toggleGroupGenre);
        actionRdBt.setUserData("Action"); adventureRdBt.setUserData("Adventure");
        comedyRdBt.setUserData("Comedy"); crimeRdBt.setUserData("Crime");
        horrorRdBt.setUserData("Horror"); romanceRdBt.setUserData("Romance");
        scifiRdBt.setUserData("Scifi"); dramaRdBt.setUserData("Drama");
        fantasyRdBt.setUserData("Fantasy"); thrillerRdBt.setUserData("Thriller");



    }

        private String opretProgram(String programnavn, String yr, String mth, String programtype, String genre, double længde){
        return creditsManagementSystem.opretProgram(programnavn,yr,mth, programtype,genre, længde);
    }

    public void opretProgramHandler(ActionEvent actionEvent) {
        if(programnavnField.getText().equals("")|| yrField.getText().equals("")|| monthField.getText().equals("") || toggleGroupProgram.getSelectedToggle() == null || toggleGroupGenre.getSelectedToggle() == null){
            resultatTxt.setText("Udfyld fylterne og vælg en knap");
        }
        else {
            resultatTxt.setText(opretProgram(programnavnField.getText(),yrField.getText(), monthField.getText(),
                    toggleGroupProgram.getSelectedToggle().getUserData().toString(),toggleGroupGenre.getSelectedToggle().getUserData().toString(),
                    Double.parseDouble(lengthField.getText())));
        }


    }

    public void backToMinsideHandler(ActionEvent actionEvent) {
        try {
            App.getStage().setScene(new Scene(loadFXML("minside")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartSideController.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}