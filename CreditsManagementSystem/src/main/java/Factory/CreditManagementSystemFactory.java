package Factory;

import domain.CreditManagement.CreditsManagementSystem;

public class CreditManagementSystemFactory {

    private static CreditsManagementSystem creditManagementSystem;

    public static CreditsManagementSystem createCreditManagementSystem(){
        if(creditManagementSystem == null ){
            creditManagementSystem = new CreditsManagementSystem();
        }
        return creditManagementSystem;
    }







}
