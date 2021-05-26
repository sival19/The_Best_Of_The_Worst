package presentation.controllers;


import Intefaces.*;
import domain.CreditsManagementSystem;
import domain.ICreditsManagementSystem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import presentation.App;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static presentation.App.loadFXML;

public class StartSideController implements Initializable {


    public TextField brugernavnField, searchField;
    public PasswordField adgangskodeField;
    public Text loginTxt;
    public ImageView programImage1, programImage2, programImage3, nextBtRight, nextBtLeft;
    public Label program1Txt, program2Txt, program3Txt;
    public ListView<ICatalogObject> searchResultView;
    public AnchorPane anchorpane;
    public Button loginBt, opretBrugerBt;
    public Button minSideBt;
    private List<ICatalogObject> searchResultList;
    private List<IProgram> programs;
    private int circularCount;
    private ICreditsManagementSystem creditsManagementSystem;
    private ObservableList<ICatalogObject> observableList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        creditsManagementSystem = CreditsManagementSystem.getCreditManagementSystem();
        searchResultView.setVisible(false);
        searchResultList = new ArrayList<>();
        observableList = FXCollections.observableArrayList();
        programs = creditsManagementSystem.getPrograms();
        circularCount = -1;
        showBrugerOptions();
        programsEmptyChecker();
        nextBtRight.setX(programImage3.getX() + 2);
        nextBtLeft.setX(programImage1.getX() - 5);
        if (creditsManagementSystem.getBrugere().size() > 0) {
            opretBrugerBt.setDisable(true);
            opretBrugerBt.setVisible(false);
        }
    }


    private void showBrugerOptions() {
        minSideBt.setVisible(creditsManagementSystem.isAdmin() || creditsManagementSystem.isProducer());
    }

    public void searchHandler(KeyEvent keyEvent) {
        observableList.removeAll(searchResultList);
        if (searchField.getText().equals("")) {
            searchResultView.setVisible(false);
        } else {
            searchResultView.setVisible(true);
            searchResultList = creditsManagementSystem.søgCredit(searchField.getText());
            observableList = FXCollections.observableList(searchResultList);
            searchResultView.setItems(observableList);
        }
    }


    public void catalogHandler(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() == searchResultView) {
            ICatalogObject iCatalogObject = searchResultView.getFocusModel().getFocusedItem();
            if (iCatalogObject instanceof IProgram) {
                creditsManagementSystem.setProgram((IProgram) iCatalogObject);
                App.getStage().setScene(new Scene(loadFXML("seProgram")));

            } else if (iCatalogObject instanceof IPerson) {
                creditsManagementSystem.setPerson((IPerson) iCatalogObject);

                App.getStage().setScene(new Scene(loadFXML("sePerson")));

                //TODO SWITCH TO sePerson.FXML
            } else if (iCatalogObject instanceof IRolle) {
                creditsManagementSystem.setRolle((IRolle) iCatalogObject);
                App.getStage().setScene(new Scene(loadFXML("seRolle")));

            }

        }
//        else if (mouseEvent.getSource() == programImage1) {
//        }
        //TODO CHANGE FROM getText to react on image
        else if (mouseEvent.getSource() == programImage1) {

            for (IProgram iProgram : programs) {
                if (iProgram.getProgramNavn().equals(program1Txt.getText())) {
                    creditsManagementSystem.setProgram(iProgram);
                    App.getStage().setScene(new Scene(loadFXML("seProgram")));
                }
            }
        } else if (mouseEvent.getSource() == programImage2) {
            for (IProgram iProgram : programs) {
                if (iProgram.getProgramNavn().equals(program2Txt.getText())) {
                    creditsManagementSystem.setProgram(iProgram);
                    App.getStage().setScene(new Scene(loadFXML("seProgram")));
                }
            }
        } else if (mouseEvent.getSource() == programImage3) {
            for (IProgram iProgram : programs) {
                if (iProgram.getProgramNavn().equals(program3Txt.getText())) {
                    creditsManagementSystem.setProgram(iProgram);
                    App.getStage().setScene(new Scene(loadFXML("seProgram")));
                }
            }
        }
    }


    private void next3ProgramImages(boolean isDirectionRight) throws URISyntaxException, MalformedURLException {
        int circular1;
        int circular2;
        int circular3;
        if (isDirectionRight) {
            circularCount++;
            circular1 = circularCount % programs.size();
            circularCount++;
            circular2 = circularCount % programs.size();
            circularCount++;
            circular3 = circularCount % programs.size();
        } else {
            circularCount = (circularCount - 1) + programs.size();
            circular3 = circularCount % programs.size();

            circularCount = (circularCount - 1) + programs.size();
            circular2 = circularCount % programs.size();

            circularCount = (circularCount - 1) + programs.size();
            circular1 = circularCount % programs.size();
        }

        IProgram program1 = programs.get(circular1);
        IProgram program2 = programs.get(circular2);
        IProgram program3 = programs.get(circular3);
        String imagePath1 = null;
        String imagePath2 = null;
        String imagePath3 = null;
        if (program1.getImagePath() != null) {
            imagePath1 = App.class.getResource("pictures/" + program1.getImagePath()).toURI().toString();
            programImage1.setImage(new Image(imagePath1));

        } else {
            programImage1.setImage(new Image(App.class.getResource("pictures/defaultMovieImage.jpg").toURI().toString()));
        }
        if (program2.getImagePath() != null) {
            imagePath2 = App.class.getResource("pictures/" + program2.getImagePath()).toURI().toString();
            programImage2.setImage(new Image(imagePath2));

        } else {
            programImage1.setImage(new Image(App.class.getResource("pictures/defaultMovieImage.jpg").toURI().toString()));
        }
        if (program3.getImagePath() != null) {
            imagePath3 = App.class.getResource("pictures/" + program3.getImagePath()).toURI().toString();
            programImage3.setImage(new Image(imagePath3));
        } else {
            programImage1.setImage(new Image(App.class.getResource("pictures/defaultMovieImage.jpg").toURI().toString()));
        }


        programImage1.setPreserveRatio(false);
        programImage1.setFitHeight(200);
        programImage1.setFitWidth(200);
        programImage2.setPreserveRatio(false);
        programImage2.setFitHeight(200);
        programImage2.setFitWidth(200);
        programImage3.setPreserveRatio(false);
        programImage3.setFitHeight(200);
        programImage3.setFitWidth(200);


        program1Txt.setText(programs.get(circular1).getProgramNavn());
        program2Txt.setText(programs.get(circular2).getProgramNavn());
        program3Txt.setText(programs.get(circular3).getProgramNavn());


    }

    private void programsEmptyChecker() {

        if (programs.size() != 0) {
            try {
                next3ProgramImages(true);
            } catch (URISyntaxException | MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            nextBtRight.setDisable(true);
            nextBtRight.setStyle("-fx-background-color:transparent;");
        }
    }


    public void nextHandler(MouseEvent actionEvent) {
        try {
            if (actionEvent.getSource() == nextBtRight) {
                next3ProgramImages(true);
            } else if (actionEvent.getSource() == nextBtLeft) {
                next3ProgramImages(false);
            }

        } catch (URISyntaxException | MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public void loginHandler(ActionEvent actionEvent) {
        try {
            if (actionEvent.getSource() == loginBt) {
                Alert loginInfo = new Alert(Alert.AlertType.CONFIRMATION);
                loginInfo.setTitle("Login");
                loginInfo.setHeaderText(null);
                String brugernavn = brugernavnField.getText();
                String adgangskode = adgangskodeField.getText();
                String resultText = creditsManagementSystem.login(brugernavn, adgangskode);
                if (resultText.equals("Velkommen!")) {
                    App.getStage().setScene(new Scene(loadFXML("startSide")));
                }
                loginInfo.setContentText(resultText);
                loginInfo.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void opretBrugerHandler(ActionEvent event) {
        try {
            App.getStage().setScene(new Scene(loadFXML("opretBruger")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void minSideHandler(ActionEvent actionEvent) {

        try {
            App.getStage().setScene(new Scene(loadFXML("minSide")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
