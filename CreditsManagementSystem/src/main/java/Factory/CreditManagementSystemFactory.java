package Factory;

import Intefaces.ICreditsManagementSystem;
import domain.creditManagement.CreditsManagementSystem;

public class CreditManagementSystemFactory {
    static ICreditsManagementSystem creditsManagementSystem;

    public static ICreditsManagementSystem getCreditManagementSystem(){
        if(creditsManagementSystem == null){
            creditsManagementSystem = new CreditsManagementSystem();
        }
        return creditsManagementSystem;
    }

}
