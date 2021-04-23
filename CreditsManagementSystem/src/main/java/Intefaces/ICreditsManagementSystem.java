package Intefaces;

import domain.logIn.Rettighed;

import java.util.List;

public interface ICreditsManagementSystem {
    boolean isAdmin();
    String opretBruger(String brugernavn, String adgangskode, String email, String rettigheder);
    String login(String brugernavn, String adgangskode);
    String opretCredit(String produktionsID, String rolletype, String personID, String beskrivelse);
    String opretPerson(String navn, String nationalitet, String fødselsdato, int personID);
    List<IDataProgram> getPrograms();
    List<IDataPerson> getPersons();
    List<IDataRolle> getRoller();

    IDataProgram getProgram();
    IDataPerson getPerson();

    IDataRolle getRolle();

    void setRolle(IDataRolle iDataRolle);
    void setProgram(IDataProgram iDataProgram);
    void setPerson(IDataPerson iDataPerson);


}
