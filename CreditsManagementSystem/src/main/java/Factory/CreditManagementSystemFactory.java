package Factory;

import Intefaces.ICreditsManagementSystem;
import domain.creditManagement.CreditsManagementSystem;
import domain.credits.Person;
import domain.credits.Program;
import domain.credits.Rolle;
import persistancy.file.FileManager;
import Intefaces.IFileManager;

public class CreditManagementSystemFactory {
    static ICreditsManagementSystem creditsManagementSystem;
    static IFileManager fileManager;


    public static ICreditsManagementSystem getCreditManagementSystem(){
        if(creditsManagementSystem == null){
            creditsManagementSystem = new CreditsManagementSystem();
        }
        return creditsManagementSystem;
    }

    public static IFileManager getFileManager(){
        if(fileManager == null){
            fileManager = new FileManager();
        }
        return fileManager;
    }


}
