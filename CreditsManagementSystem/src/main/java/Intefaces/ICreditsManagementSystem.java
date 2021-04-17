package Intefaces;

import domain.logIn.Rettighed;

import java.util.List;

public interface ICreditsManagementSystem {
    boolean isAdmin();
    String opretBruger(String brugernavn, String adgangskode, String email, String rettigheder);
    String login(String brugernavn, String adgangskode);
    String opretCredit(String produktionsID, String rolletype, String personID, String beskrivelse);
    List<IDataProgram> getPrograms();
    List<IDataPerson> getPersons();
    List<IDataRolle> getRolle();
}
