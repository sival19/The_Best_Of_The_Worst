package domain;

import Intefaces.*;

import java.util.List;

public interface ICreditsManagementSystem {
    boolean isAdmin();
    boolean isProducer();
    IBruger getBruger();
    List<IBruger> getBrugere();
    String opretBruger(String brugernavn, String adgangskode, String email, String rettigheder);
    String opretProgram(String programnavn, String yr,String mth, String programtype, String genre, double længde );
    String login(String brugernavn, String adgangskode);
    String opretCredit(String produktionsID, String rolletype, String personID, String beskrivelse);
    String opretPerson(String navn, String nationalitet, String fødselsdato);

    String opretRolle(String rolletype);
    List<IProgram> getPrograms();
    List<IPerson> getPersons();
    List<IRolle> getRoller();
    List<ICatalogObject> søgCredit(String søgeord);


    IProgram getProgram();
    IPerson getPerson();
    IRolle getRolle();


    void setRolle(IRolle iRolle);
    void setProgram(IProgram iProgram);
    void setPerson(IPerson iPerson);

    String getBrugerrettighed();



}
