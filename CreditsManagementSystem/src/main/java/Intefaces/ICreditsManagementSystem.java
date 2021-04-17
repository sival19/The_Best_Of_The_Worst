package Intefaces;

import domain.logIn.Rettighed;

public interface ICreditsManagementSystem {
    boolean isAdmin();
    String opretBruger(String brugernavn, String adgangskode, String email, String rettigheder);
    String login(String brugernavn, String adgangskode);
    String opretCredit(int produktionsID, String rolletype, int personID, String beskrivelse);
}
