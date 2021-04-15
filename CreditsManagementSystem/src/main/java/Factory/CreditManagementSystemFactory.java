package Factory;

import domain.ICreditsManagementSystem;
import domain.creditManagement.CreditsManagementSystem;
import peristancy.file.FileManager;
import peristancy.file.IFileManager;

public class CreditManagementSystemFactory {
    static ICreditsManagementSystem creditsManagementSystem;
    static IFileManager fileManager;

    public static ICreditsManagementSystem createCreditManagementSystem(){
        if(creditsManagementSystem == null){
            creditsManagementSystem = new CreditsManagementSystem();
        }
        return creditsManagementSystem;
    }

    public static IFileManager createFileManager(){
        if(fileManager == null){
            fileManager = new FileManager();
        }
        return fileManager;
    }


}
