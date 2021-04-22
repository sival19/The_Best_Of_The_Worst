package presentation;

import Factory.CreditManagementSystemFactory;
import Intefaces.*;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
    public Canvas canvasProgramImages;
    public Button opretProgramBt;
    GraphicsContext graphicsContext;
    AnimationTimer animationTimer;
    Image image1;
    Image image2;
    Image image3;
    private ICreditsManagementSystem creditsManagementSystem;
    private ObservableList<ICatalogObject> observableList;
    List<ICatalogObject> searchResultList;
    List<IDataProgram> programs;
    int circularCount;

    private String login(String brugernavn, String password){
        return creditsManagementSystem.login(brugernavn,password);
    }

    public void loginHandler(ActionEvent event){
        loginTxt.setText(login(brugernavnField.getText(),adgangskodeField.getText()));
        if(loginTxt.getText().equalsIgnoreCase("Velkommen!")){
            try {
                App.getStage().setScene(new Scene(loadFXML("startSide")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("logget ind");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        creditsManagementSystem = CreditManagementSystemFactory.getCreditManagementSystem();
        canvasProgramImages = new Canvas();
        graphicsContext = canvasProgramImages.getGraphicsContext2D();
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                drawCanvas();
            }
        };
        showBrugerOptions();
        searchResultView.setStyle("-fx-background-color: transparent");
        searchResultList = new ArrayList<>();
        observableList = FXCollections.observableArrayList();
        programs = creditsManagementSystem.getPrograms();
        circularCount = -1;
        programsEmptyChecker();
        animationTimer.start();
    }

    private void drawCanvas(){
        graphicsContext.drawImage(image1,0,canvasProgramImages.getLayoutY(),canvasProgramImages.getWidth()/3,canvasProgramImages.getHeight());
        graphicsContext.drawImage(image2,0,canvasProgramImages.getLayoutY(),(canvasProgramImages.getWidth()/3)*2,canvasProgramImages.getHeight());
        graphicsContext.drawImage(image3,0,canvasProgramImages.getLayoutY(),canvasProgramImages.getWidth(),canvasProgramImages.getHeight());


    }

    private void showBrugerOptions(){
        String brugerRettighed = creditsManagementSystem.getBrugerrettighed();
        if(brugerRettighed.equalsIgnoreCase("Administrator")){
            //TODO SET NODES THAT ARE FOR ADMIN TO VISIBLE
        }
        else if(brugerRettighed.equalsIgnoreCase("Producer")){
            //TODO SET NODES THAT ARE FOR PRODUCER TO VISIBLE
        }
    }

    public void searchHandler(KeyEvent keyEvent){
        observableList.removeAll(searchResultList);
        if(searchField.getText().equals("")) {
            searchResultView.setStyle("-fx-background-color: transparent");
        }
        else {
            searchResultView.setDisable(false);
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


    private void next3ProgramImages(boolean isDirectionRight) {
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


        try {
            image1 = new Image(StartSideController.class.getResource(programs.get(circular1).getImagePath()).toURI().toString());
            image2 = new Image(StartSideController.class.getResource(programs.get(circular2).getImagePath()).toURI().toString());
            image3 = new Image(StartSideController.class.getResource(programs.get(circular3).getImagePath()).toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        program1Txt.setText(programs.get(circular1).getProgramNavn()); program1Txt.setX(((canvasProgramImages.getWidth()/3)/2));
        program2Txt.setText(programs.get(circular2).getProgramNavn()); program2Txt.setX(((canvasProgramImages.getWidth()/3)*2)/2);
        program3Txt.setText(programs.get(circular3).getProgramNavn()); program3Txt.setX(canvasProgramImages.getWidth()/2);

    }

    public void programsEmptyChecker(){

        if(programs.size() > 2){
            next3ProgramImages(true);
        }
        else{
            nextBtRight.setDisable(true);
            nextBtRight.setStyle("-fx-background-color:transparent;");
        }
    }

    public void nextHandler(ActionEvent actionEvent){
        if(actionEvent.getSource()==nextBtRight){
            next3ProgramImages(true);
            animationTimer.start();
        }
        else if(actionEvent.getSource()==nextBtLeft){
            next3ProgramImages(false);
            animationTimer.stop();
        }

    }



    public void opretBrugerHandler(ActionEvent event) {
        try {
            App.getStage().setScene(new Scene(loadFXML("opretBruger")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void opretCreditHandler(ActionEvent actionEvent) {
    }

    public void opretProgramHandler(ActionEvent actionEvent) {
        try {
            App.getStage().setScene(new Scene(loadFXML("opretProgram")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
