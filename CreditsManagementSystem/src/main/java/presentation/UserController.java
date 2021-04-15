package presentation;

import Factory.CreditManagementSystemFactory;
import domain.ICreditsManagementSystem;
import domain.logIn.Rettighed;

public class UserController {

    ICreditsManagementSystem creditsManagementSystem;


    public void initialize(){
        creditsManagementSystem = CreditManagementSystemFactory.createCreditManagementSystem();
        if(creditsManagementSystem.isAdmin()){
            //TODO SHOW ADMINISTRATOR OPTIONS ON SCREEN
        }
        else{
            //TODO HIDE ADMINISTRATOR OPTIONS ON SCREEN
        }
    }
    public String opretBruger(String brugernavn, String adgangskode, String email, String rettighed){
        return creditsManagementSystem.opretBruger(brugernavn, adgangskode, email, rettighed);
    }
}
