package Factory;

import Intefaces.IDataManager;
import persistancy.database.DatabaseManager;
import persistancy.file.FileManager;

public class DataManagementFactory {
    static IDataManager iDataManager;

    public static IDataManager createDataManager(String type){
        if(type.equalsIgnoreCase("file")){
            if(iDataManager == null){
                iDataManager = new FileManager();
            }
        }
        else if(type.equalsIgnoreCase("Database")){
            if(iDataManager == null){
                iDataManager = new DatabaseManager();
            }
        }
        return iDataManager;
    }
}
