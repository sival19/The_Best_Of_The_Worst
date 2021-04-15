package presentation;

import Factory.CreditManagementSystemFactory;
import domain.ICreditsManagementSystem;

public class StartSideController {

    ICreditsManagementSystem creditsManagementSystem;

    public void initialize(){
        creditsManagementSystem = CreditManagementSystemFactory.createCreditManagementSystem();
    }

    public String login(String brugernavn, String password){
        return creditsManagementSystem.login(brugernavn,password);
    }
}
