package presentation.controllers;

import domain.ICreditsManagementSystem;
import domain.CreditsManagementSystem;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import presentation.App;

import java.io.IOException;

import static presentation.App.loadFXML;

public class UserController {
    //Der er ændring her

    public Button tilbage;
    public Button opretBt;
    public TextField username, password, eMail;
    public RadioButton admin, producer;
    public Label resultText;
    private ICreditsManagementSystem creditsManagementSystem;
    private ToggleGroup toggleGroup;
    private String rettigheder;

    public void initialize() {
        toggleGroup = new ToggleGroup();
        admin.setToggleGroup(toggleGroup);
        producer.setToggleGroup(toggleGroup);
        creditsManagementSystem = CreditsManagementSystem.getCreditManagementSystem();
        if (creditsManagementSystem.getBrugere().size() == 0) {
            producer.setDisable(true);
        }

    }


    public void backScreen(ActionEvent event) {
        try {
            if (creditsManagementSystem.getBruger().getRettighed().toString().equals("Seer")) {
                App.getStage().setScene(new Scene(loadFXML("startSide")));

            } else App.getStage().setScene(new Scene(loadFXML("minSide")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void opretBrugerHandler(ActionEvent event) {
        rettigheder = "";

        if (admin.isSelected()) {
            rettigheder = "Administrator";
        } else if (producer.isSelected()) {
            rettigheder = "Producer";
        }
        resultText.setText(creditsManagementSystem.opretBruger(username.getText(), password.getText(), eMail.getText(), rettigheder));
    }
}
