package presentation;

import Factory.CreditManagementSystemFactory;
import Intefaces.ICreditsManagementSystem;

public class CreditsController {
    ICreditsManagementSystem creditsManagementSystem;

    public void initialize(){
        creditsManagementSystem = CreditManagementSystemFactory.createCreditManagementSystem();
    }

    public void opretCredit(int produktionsID,String rolletype, int personID, String beskrivelse){
        creditsManagementSystem.opretCredit(produktionsID,rolletype,personID, beskrivelse);

    }
}
