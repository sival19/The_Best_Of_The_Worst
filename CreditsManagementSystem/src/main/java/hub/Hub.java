package hub;

import Intefaces.ICreditsManagementSystem;
import Intefaces.IDataManager;
import Intefaces.IHub;
import domain.creditManagement.CreditsManagementSystem;
import persistancy.database.DatabaseManager;
import persistancy.file.FileManager;

public class Hub implements IHub {

    ICreditsManagementSystem creditsManagementSystem;
    IDataManager iDataManager;
    public  ICreditsManagementSystem getCreditManagementSystem(){
        return creditsManagementSystem = CreditsManagementSystem.getCreditManagementSystem();
    }

    public IDataManager getDataManager(String type){
        if(type.equalsIgnoreCase("file")){
            iDataManager= FileManager.getFileManager();
        }
        else if(type.equalsIgnoreCase("Database")){
            iDataManager = DatabaseManager.getDatabase();
        }
        return iDataManager;
    }

}
