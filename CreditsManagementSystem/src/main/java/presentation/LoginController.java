package presentation;


import Factory.CreditManagementSystemFactory;
import Intefaces.ICreditsManagementSystem;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;



import static presentation.App.loadFXML;


public class LoginController implements Initializable {

    ICreditsManagementSystem creditsManagementSystem;


    public String login(String brugernavn, String password) {
        return creditsManagementSystem.login(brugernavn, password);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        creditsManagementSystem = CreditManagementSystemFactory.getCreditManagementSystem();
    }

}
