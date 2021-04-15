package domain.creditManagement;

import domain.credits.Program;
import domain.credits.Rolle;

import java.util.Date;

public class CreditsManagementSystem {

    private Program program;

    public void CreditsManagementSystem() {

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

    public String opretProgram(String programNavn, int produktionsID, Date udgivelsesDato, Enum programType, String genre, double længde) {
        return null;
    }

    public String opretBruger(String brugernavn, String kodeord, String email, boolean isAdmin, int brugerID, String navn) {
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

}
