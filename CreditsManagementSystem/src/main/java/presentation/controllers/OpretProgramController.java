package presentation.controllers;

import domain.ICreditsManagementSystem;
import domain.CreditsManagementSystem;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import presentation.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static presentation.App.loadFXML;

public class OpretProgramController implements Initializable {
    public Button opretBt;

    public RadioButton dokumentarRdBt, filmRdBt, kortFilmRdBt, serieRdBt;
    public RadioButton actionRdBt, adventureRdBt, comedyRdBt, crimeRdBt,
            horrorRdBt, romanceRdBt, scifiRdBt, dramaRdBt, fantasyRdBt, thrillerRdBt;

    public ToggleGroup toggleGroupProgramType;
    public ToggleGroup toggleGroupGenre;

    public Button tilbage;
    public TextField programnavnField;
    public TextField monthField, yrField, lengthField;
    public Label resultatText;
    private ICreditsManagementSystem creditsManagementSystem;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        creditsManagementSystem = CreditsManagementSystem.getCreditManagementSystem();
        toggleGroupProgramType = new ToggleGroup();
        toggleGroupGenre = new ToggleGroup();
        dokumentarRdBt.setToggleGroup(toggleGroupProgramType);
        filmRdBt.setToggleGroup(toggleGroupProgramType);
        kortFilmRdBt.setToggleGroup(toggleGroupProgramType);
        serieRdBt.setToggleGroup(toggleGroupProgramType);
        dokumentarRdBt.setUserData("Dokumentar");
        filmRdBt.setUserData("Film");
        kortFilmRdBt.setUserData("KortFilm");
        serieRdBt.setUserData("Serie");

        actionRdBt.setToggleGroup(toggleGroupGenre);
        adventureRdBt.setToggleGroup(toggleGroupGenre);
        comedyRdBt.setToggleGroup(toggleGroupGenre);
        crimeRdBt.setToggleGroup(toggleGroupGenre);
        horrorRdBt.setToggleGroup(toggleGroupGenre);
        romanceRdBt.setToggleGroup(toggleGroupGenre);
        scifiRdBt.setToggleGroup(toggleGroupGenre);
        dramaRdBt.setToggleGroup(toggleGroupGenre);
        fantasyRdBt.setToggleGroup(toggleGroupGenre);
        thrillerRdBt.setToggleGroup(toggleGroupGenre);
        actionRdBt.setUserData("Action");
        adventureRdBt.setUserData("Adventure");
        comedyRdBt.setUserData("Comedy");
        crimeRdBt.setUserData("Crime");
        horrorRdBt.setUserData("Horror");
        romanceRdBt.setUserData("Romance");
        scifiRdBt.setUserData("Scifi");
        dramaRdBt.setUserData("Drama");
        fantasyRdBt.setUserData("Fantasy");
        thrillerRdBt.setUserData("Thriller");


    }


    public void opretProgramHandler(ActionEvent actionEvent) {
        if (programnavnField.getText().equals("") || yrField.getText().equals("") || monthField.getText().equals("") || toggleGroupProgramType.getSelectedToggle() == null || toggleGroupGenre.getSelectedToggle() == null) {
            resultatText.setText("Udfyld felterne og vælg en knap");
        } else {
            resultatText.setText(creditsManagementSystem.opretProgram(programnavnField.getText(), yrField.getText(), monthField.getText(),
                    toggleGroupProgramType.getSelectedToggle().getUserData().toString(), toggleGroupGenre.getSelectedToggle().getUserData().toString(),
                    Double.parseDouble(lengthField.getText())));
        }


    }

    public void backToMinsideHandler(ActionEvent event) {
        try {
            App.getStage().setScene(new Scene(loadFXML("minside")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}