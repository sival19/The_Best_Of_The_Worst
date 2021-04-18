package presentation;

import Factory.CreditManagementSystemFactory;
import Intefaces.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import persistancy.file.FileManager;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
    public ListView searchResultView;
    public Button nextImageBt;
    private ICreditsManagementSystem creditsManagementSystem;
    private ObservableList<ICatalogObject> observableList;
    List<ICatalogObject> searchResultList;
    List<IDataProgram> programs;
    int circularCount;

    public String login(String brugernavn, String password){
        return creditsManagementSystem.login(brugernavn,password);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        creditsManagementSystem = CreditManagementSystemFactory.getCreditManagementSystem();
        searchResultView.setStyle("-fx-background-color: transparent");
        searchResultList = new ArrayList<>();
        observableList = FXCollections.observableArrayList();
        programs = creditsManagementSystem.getPrograms();
        circularCount = -1;
        programsEmptyChecker();
    }

    public void searchHandler(KeyEvent keyEvent){
        observableList.removeAll(searchResultList);
        if(searchField.getText().equals("")) {
            searchResultView.setStyle("-fx-background-color: transparent");
        }
        else {

            searchResultView.setStyle("-fx-background-color: white");
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
            for(IDataRolle iDataRolle: creditsManagementSystem.getRolle()){
                if(iDataRolle.getRolletype().toLowerCase().contains(searchField.getText().toLowerCase())){
                    searchResultList.add(iDataRolle);
                }
            }
            observableList = FXCollections.observableList(searchResultList);
            searchResultView.setItems(observableList);


        }


    }

    public void listViewHandler(MouseEvent mouseEvent){


    }


    private void next3ProgramImages() throws URISyntaxException, MalformedURLException {
        circularCount++;
        int circular1 = circularCount%programs.size();
        circularCount++;
        int circular2 = circularCount%programs.size();
        circularCount++;
        int circular3 = circularCount%programs.size();

        programImage1.setImage(new Image(String.valueOf(StartSideController.class.getResource(programs.get(circular1).getImagePath()).toURI().toURL())));
        programImage2.setImage(new Image(String.valueOf(StartSideController.class.getResource(programs.get(circular2).getImagePath()).toURI().toURL())));
        programImage3.setImage(new Image(String.valueOf(StartSideController.class.getResource(programs.get(circular3).getImagePath()).toURI().toURL())));
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
                next3ProgramImages();
            } catch (URISyntaxException | MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else{
            nextImageBt.setDisable(true);
            nextImageBt.setStyle("-fx-background-color:transparent;");
        }
    }

    public void nextHandler(ActionEvent actionEvent){
        try {
            next3ProgramImages();
        } catch (URISyntaxException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
