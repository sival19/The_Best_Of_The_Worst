package domain.creditManagement;

import domain.ICreditsManagementSystem;
import domain.credits.Program;
import domain.credits.ProgramType;
import domain.credits.Rolle;
import domain.logIn.Rettighed;
import domain.logIn.UserManager;

import java.util.Date;

public class CreditsManagementSystem implements ICreditsManagementSystem {
    private UserManager userManager;
    private Catalog catalog;


    public CreditsManagementSystem() {

    }

    public String opretCredit(int produktionsID, String rolletype, int personID) {
        return null;
    }

    public String opretPerson(String navn, String nationalitet, Date fødselsdato) {
        return null;
    }

    public String opretRolle(String rolletype) {
        return null;
    }

    public String opretProgram(String programNavn, int produktionsID, Date udgivelsesDato, ProgramType programType, String genre, double længde) {
        return null;
    }

    public String seProgram(int produktionsID) {
        return null;
    }

    public String sePerson(int personID) {
        return null;
    }

    public String seRolle(Rolle rolletype) {
        return null;
    }

    public String søgCredit(String søgeord) {
        return null;
    }

    @Override
    public boolean isAdmin() {
        return userManager.isAdmin();
    }

    @Override
    public String opretBruger(String brugernavn, String adgangskode, String email, String rettighed) {
        return userManager.opretBruger(brugernavn, adgangskode, email, rettighed);
    }

    @Override
    public String login(String brugernavn, String adgangskode) {
        return userManager.validereBruger(brugernavn, adgangskode);
    }
}
