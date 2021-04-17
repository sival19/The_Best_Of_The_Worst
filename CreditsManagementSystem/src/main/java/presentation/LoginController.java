package presentation;

import Factory.CreditManagementSystemFactory;
import Intefaces.ICreditsManagementSystem;

public class LoginController {

    ICreditsManagementSystem creditsManagementSystem;

    public void initialize(){
        creditsManagementSystem = CreditManagementSystemFactory.getCreditManagementSystem();
    }

    public String login(String brugernavn, String password){
        return creditsManagementSystem.login(brugernavn,password);
    }
}
