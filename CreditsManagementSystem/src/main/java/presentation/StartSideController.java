package presentation;

import Factory.CreditManagementSystemFactory;
import Intefaces.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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
        circularCount = 0;
        try {
            next3ProgramImages();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

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


    private void next3ProgramImages() throws URISyntaxException, MalformedURLException {
        int circular1 = circularCount++%programs.size();
        int circular2 = circularCount++%programs.size();
        int circular3 = circularCount++%programs.size();
        System.out.println(StartSideController.class.getResource(programs.get(circular1).getImagePath()).getPath());
        System.out.println(StartSideController.class.getResource(programs.get(circular2).getImagePath()).getPath());
        programImage1.setImage(new Image(String.valueOf(StartSideController.class.getResource(programs.get(circular1).getImagePath()).toURI().toURL())));
        programImage2.setImage(new Image(String.valueOf(StartSideController.class.getResource(programs.get(circular2).getImagePath()).toURI().toURL())));
        programImage3.setImage(new Image(String.valueOf(StartSideController.class.getResource(programs.get(circular3).getImagePath()).toURI().toURL())));
        program1Txt.setText(programs.get(circular1).getProgramNavn());
        program2Txt.setText(programs.get(circular2).getProgramNavn());
        program3Txt.setText(programs.get(circular3).getProgramNavn());



    }

}
