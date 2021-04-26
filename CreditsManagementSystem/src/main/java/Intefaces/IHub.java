package Intefaces;

public interface IHub {

    ICreditsManagementSystem getCreditManagementSystem();

    IDataManager getDataManager(String type);
}
