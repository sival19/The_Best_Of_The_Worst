package Intefaces;

import java.util.List;

public interface ICreditsManagementSystem {
    boolean isAdmin();
    String opretBruger(String brugernavn, String adgangskode, String email, String rettigheder);
    String opretProgram(String programnavn, String yr,String mth, String day, String programtype, String genre, double længde );
    String login(String brugernavn, String adgangskode);
    String opretCredit(String produktionsID, String rolletype, String personID, String beskrivelse);
    List<IDataProgram> getPrograms();
    List<IDataPerson> getPersons();
    List<IDataRolle> getRoller();

    IDataProgram getProgram();
    IDataPerson getPerson();

    IDataRolle getRolle();

    void setRolle(IDataRolle iDataRolle);
    void setProgram(IDataProgram iDataProgram);
    void setPerson(IDataPerson iDataPerson);

    String getBrugerrettighed();



}
