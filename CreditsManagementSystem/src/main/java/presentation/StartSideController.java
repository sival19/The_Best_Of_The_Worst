package presentation;

import Factory.CreditManagementSystemFactory;
import Intefaces.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static presentation.App.loadFXML;

public class StartSideController implements Initializable {


    public TextField brugernavnField;
    public PasswordField adgangskodeField;
    public Button loginBt;
    public Button opretBrugerBt;
    public Text loginTxt;
    public ImageView programImage1;
    public ImageView programImage2;
    public ImageView programImage3;
    public Text program1Txt;
    public Text program2Txt;
    public Text program3Txt;
    public TextField searchField;
    public ListView<ICatalogObject> searchResultView;
    public Button nextBtRight;
    public Button nextBtLeft;
    public AnchorPane anchorpane;
    private ICreditsManagementSystem creditsManagementSystem;
    private ObservableList<ICatalogObject> observableList;
    List<ICatalogObject> searchResultList;
    List<IDataProgram> programs;
    int circularCount;
    public Button opretCredit;

    public String login(String brugernavn, String password){
        return creditsManagementSystem.login(brugernavn,password);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        creditsManagementSystem = CreditManagementSystemFactory.getCreditManagementSystem();
        searchResultView.setVisible(false);
        searchResultList = new ArrayList<>();
        observableList = FXCollections.observableArrayList();
        programs = creditsManagementSystem.getPrograms();
        circularCount = -1;
        programsEmptyChecker();
    }

    public void searchHandler(KeyEvent keyEvent){
        observableList.removeAll(searchResultList);
        if(searchField.getText().equals("")) {
            searchResultView.setVisible(false);
        }
        else {
            searchResultView.setVisible(true);
            searchResultList = new ArrayList<>();
            for(IDataPerson iDataPerson: creditsManagementSystem.getPersons()){
                if(iDataPerson.getNavn().toLowerCase().contains(searchField.getText().toLowerCase())){
                    searchResultList.add(iDataPerson);
                }
            }
            for(IDataProgram iDataProgram: creditsManagementSystem.getPrograms()){
                if(iDataProgram.getProgramNavn().toLowerCase().contains(searchField.getText().toLowerCase())){
                    searchResultList.add(iDataProgram);
                }
            }
            for(IDataRolle iDataRolle: creditsManagementSystem.getRoller()){
                if(iDataRolle.getRolletype().toLowerCase().contains(searchField.getText().toLowerCase())){
                    searchResultList.add(iDataRolle);
                }
            }
            observableList = FXCollections.observableList(searchResultList);
            searchResultView.setItems(observableList);


        }


    }



    public void catalogHandler(MouseEvent mouseEvent) throws IOException {
        CreditsController creditsController = new CreditsController();
        if(mouseEvent.getSource()==searchResultView){
            ICatalogObject iCatalogObject = searchResultView.getFocusModel().getFocusedItem();
            System.out.println(searchResultView.getFocusModel().getFocusedItem());
            if(iCatalogObject instanceof IDataProgram){
                creditsManagementSystem.setProgram((IDataProgram) iCatalogObject);
                App.getStage().setScene(new Scene(loadFXML("seProgram")));

            }
            else if(iCatalogObject instanceof IDataPerson){
                creditsManagementSystem.setPerson((IDataPerson) iCatalogObject);
                //TODO SWITCH TO sePerson.FXML
            }
            else if(iCatalogObject instanceof IDataRolle){
                creditsManagementSystem.setRolle((IDataRolle)iCatalogObject);
                App.getStage().setScene(new Scene(loadFXML("seRolle")));

            }
        }
        else if(mouseEvent.getSource()==programImage1){
            for(IDataProgram iDataProgram: programs){
                if(iDataProgram.getProgramNavn().equals(program1Txt.getText())){
                    creditsManagementSystem.setProgram(iDataProgram);
                    System.out.println(iDataProgram);
                    App.getStage().setScene(new Scene(loadFXML("seProgram")));
                }
            }
        }
        else if(mouseEvent.getSource()==programImage2){
            for(IDataProgram iDataProgram: programs){
                if(iDataProgram.getProgramNavn().equals(program2Txt.getText())){
                    creditsManagementSystem.setProgram(iDataProgram);
                    System.out.println(iDataProgram);
                    App.getStage().setScene(new Scene(loadFXML("seProgram")));
                }
            }
        }
        else if(mouseEvent.getSource()==programImage3){
            for(IDataProgram iDataProgram: programs){
                if(iDataProgram.getProgramNavn().equals(program3Txt.getText())){
                    creditsManagementSystem.setProgram(iDataProgram);
                    System.out.println(iDataProgram);
                    App.getStage().setScene(new Scene(loadFXML("seprogram")));
                }
            }
        }
    }


    private void next3ProgramImages(boolean isDirectionRight) throws URISyntaxException, MalformedURLException {
        int circular1;
        int circular2;
        int circular3;
        if(isDirectionRight){
            circularCount++;
            circular1 = circularCount%programs.size();
            circularCount++;
            circular2 = circularCount%programs.size();
            circularCount++;
            circular3 = circularCount%programs.size();
        }
        else{
            circularCount = (circularCount-1)+programs.size();
            circular3 = circularCount%programs.size();

            circularCount = (circularCount-1)+programs.size();
            circular2 = circularCount%programs.size();

            circularCount = (circularCount-1)+programs.size();
            circular1 = circularCount%programs.size();
        }


        programImage1.setImage(new Image(StartSideController.class.getResource(programs.get(circular1).getImagePath()).toURI().toString()));
        programImage2.setImage(new Image(StartSideController.class.getResource(programs.get(circular2).getImagePath()).toURI().toString()));
        programImage3.setImage(new Image(StartSideController.class.getResource(programs.get(circular3).getImagePath()).toURI().toString()));
        programImage1.setFitHeight(200); programImage1.setFitWidth(200);programImage1.setPreserveRatio(false);
        programImage2.setFitHeight(200); programImage2.setFitWidth(200);programImage2.setPreserveRatio(false);
        programImage3.setFitHeight(200); programImage3.setFitWidth(200);programImage3.setPreserveRatio(false);

        program1Txt.setText(programs.get(circular1).getProgramNavn()); program1Txt.setX((programImage1.getX()+programImage1.getFitWidth())/2);
        program2Txt.setText(programs.get(circular2).getProgramNavn()); program2Txt.setX((programImage2.getX()+programImage2.getFitWidth())/2);
        program3Txt.setText(programs.get(circular3).getProgramNavn()); program3Txt.setX((programImage3.getX()+programImage3.getFitWidth())/2);

    }

    public void programsEmptyChecker(){

        if(programs.size() != 0){
            try {
                next3ProgramImages(true);
            } catch (URISyntaxException | MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else{
            nextBtRight.setDisable(true);
            nextBtRight.setStyle("-fx-background-color:transparent;");
        }
    }

    public void nextHandler(ActionEvent actionEvent){
        try {
            if(actionEvent.getSource()==nextBtRight){
                next3ProgramImages(true);
            }
            else if(actionEvent.getSource()==nextBtLeft){
                next3ProgramImages(false);
            }

        } catch (URISyntaxException | MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public void loginHandler(ActionEvent actionEvent) {
        try {
            if(actionEvent.getSource()==loginBt){
                Alert loginInfo = new Alert (Alert.AlertType.CONFIRMATION);
                loginInfo.setTitle("Login");
                loginInfo.setHeaderText(null);
                String brugernavn = brugernavnField.getText();
                String adgangskode = adgangskodeField.getText();

                loginInfo.setContentText(login(brugernavn, adgangskode));
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

    public void opretCreditHandler(ActionEvent event) {
        try{
            App.getStage().setScene(new Scene(loadFXML("opretCredit")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
