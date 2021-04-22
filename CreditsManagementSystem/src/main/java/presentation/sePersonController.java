package presentation;

import Factory.CreditManagementSystemFactory;
import Intefaces.ICreditsManagementSystem;
import Intefaces.IDataPerson;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class sePersonController implements Initializable {

    ICreditsManagementSystem iCreditsManagementSystem;
    public Button backToStart;
    public Label personNavn;
    public Label personFoedselsdato;
    public Label personNationalitet;
    public ImageView personImage;
    public TextArea personTextArea;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iCreditsManagementSystem = CreditManagementSystemFactory.getCreditManagementSystem();
        IDataPerson person = iCreditsManagementSystem.getPerson();
        personNavn.setText(person.getNavn());
        personFoedselsdato.setText(person.getFoedselsdato().toString());
        personNationalitet.setText(person.getNationalitet());

        try {
            personImage.setImage(new Image(String.valueOf(StartSideController.class.getResource(person.getImagePath()).toURI().toURL())));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }



    public void backtoStartSideHandler() throws IOException {
        App.getStage().setScene(new Scene(loadFXML("startSide")));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartSideController.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
