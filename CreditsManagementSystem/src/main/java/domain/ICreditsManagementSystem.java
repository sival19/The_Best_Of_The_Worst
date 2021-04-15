package domain;

import domain.logIn.Rettighed;

public interface ICreditsManagementSystem {
    boolean isAdmin();
    String opretBruger(String brugernavn, String adgangskode, String email, String rettigheder);
    String login(String brugernavn, String adgangskode);
}
