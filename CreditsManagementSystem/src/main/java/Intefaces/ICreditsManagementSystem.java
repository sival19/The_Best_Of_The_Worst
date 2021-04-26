package Intefaces;

import java.util.List;

public interface ICreditsManagementSystem {
    boolean isAdmin();
    boolean isProducer();
    IDataBruger getBruger();
    String opretBruger(String brugernavn, String adgangskode, String email, String rettigheder);
    String opretProgram(String programnavn, String yr,String mth, String programtype, String genre, double længde );
    String login(String brugernavn, String adgangskode);
    String opretCredit(String produktionsID, String rolletype, String personID, String beskrivelse);
    String opretPerson(String navn, String nationalitet, String fødselsdato);

    String opretRolle(String rolletype);
    List<IDataProgram> getPrograms();
    List<IDataPerson> getPersons();
    List<IDataRolle> getRoller();
    List<ICatalogObject> søgCredit(String søgeord);


    IDataProgram getProgram();
    IDataPerson getPerson();

    IDataRolle getRolle();

    void setRolle(IDataRolle iDataRolle);
    void setProgram(IDataProgram iDataProgram);
    void setPerson(IDataPerson iDataPerson);

    String getBrugerrettighed();



}
