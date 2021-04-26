package presentation;


import Intefaces.*;
import hub.Hub;
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
    public Button  loginBt, opretBrugerBt;
    public Button opret;
    public Button minSideBt;
    private List<ICatalogObject> searchResultList;
    private List<IDataProgram> programs;
    private int circularCount;
    private ICreditsManagementSystem creditsManagementSystem;
    private ObservableList<ICatalogObject> observableList;
    private IHub hub;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hub = new Hub();
        creditsManagementSystem = hub.getCreditManagementSystem();
        searchResultView.setVisible(false);
        searchResultList = new ArrayList<>();
        observableList = FXCollections.observableArrayList();
        programs = creditsManagementSystem.getPrograms();
        circularCount = -1;
        showBrugerOptions();
        programsEmptyChecker();
        nextBtRight.setX(programImage3.getX()+2);
        nextBtLeft.setX(programImage1.getX()-5);
    }

    public String login(String brugernavn, String password) {
        return creditsManagementSystem.login(brugernavn, password);
    }
   private void showBrugerOptions(){
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
        CreditsController creditsController = new CreditsController();
        if (mouseEvent.getSource() == searchResultView) {
            ICatalogObject iCatalogObject = searchResultView.getFocusModel().getFocusedItem();
            System.out.println(searchResultView.getFocusModel().getFocusedItem());
            if (iCatalogObject instanceof IDataProgram) {
                creditsManagementSystem.setProgram((IDataProgram) iCatalogObject);
                App.getStage().setScene(new Scene(loadFXML("seProgram")));

            } else if (iCatalogObject instanceof IDataPerson) {
                creditsManagementSystem.setPerson((IDataPerson) iCatalogObject);

                App.getStage().setScene(new Scene(loadFXML("sePerson")));

                //TODO SWITCH TO sePerson.FXML
            } else if (iCatalogObject instanceof IDataRolle) {
                creditsManagementSystem.setRolle((IDataRolle) iCatalogObject);
                App.getStage().setScene(new Scene(loadFXML("seRolle")));

            }

        }
//        else if (mouseEvent.getSource() == programImage1) {
//        }
        //TODO CHANGE FROM getText to react on image
        else if (mouseEvent.getSource() == programImage1) {

            for (IDataProgram iDataProgram : programs) {
                if (iDataProgram.getProgramNavn().equals(program1Txt.getText())) {
                    creditsManagementSystem.setProgram(iDataProgram);
                    System.out.println(iDataProgram);
                    App.getStage().setScene(new Scene(loadFXML("seProgram")));
                }
            }
        } else if (mouseEvent.getSource() == programImage2) {
            for (IDataProgram iDataProgram : programs) {
                if (iDataProgram.getProgramNavn().equals(program2Txt.getText())) {
                    creditsManagementSystem.setProgram(iDataProgram);
                    System.out.println(iDataProgram);
                    App.getStage().setScene(new Scene(loadFXML("seProgram")));
                }
            }
        } else if (mouseEvent.getSource() == programImage3) {
            for (IDataProgram iDataProgram : programs) {
                if (iDataProgram.getProgramNavn().equals(program3Txt.getText())) {
                    creditsManagementSystem.setProgram(iDataProgram);
                    System.out.println(iDataProgram);
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

        IDataProgram program1 = programs.get(circular1);
        IDataProgram program2 = programs.get(circular2);
        IDataProgram program3 = programs.get(circular3);
        String imagePath1 = null;
        String imagePath2 = null;
        String imagePath3 = null;
        if(program1.getImagePath()!=null){
            imagePath1 = StartSideController.class.getResource(program1.getImagePath()).toURI().toString();
            programImage1.setImage(new Image(imagePath1));

        }
        if(program2.getImagePath()!=null){
            imagePath2 = StartSideController.class.getResource(program2.getImagePath()).toURI().toString();
            programImage2.setImage(new Image(imagePath2));

        }
        if(program3.getImagePath()!=null){
            imagePath3 = StartSideController.class.getResource(program3.getImagePath()).toURI().toString();
            programImage3.setImage(new Image(imagePath3));
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
                String resultText = login(brugernavn, adgangskode);
                if(resultText.equals("Velkommen!")){
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

        try{
            App.getStage().setScene(new Scene(loadFXML("minSide")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
