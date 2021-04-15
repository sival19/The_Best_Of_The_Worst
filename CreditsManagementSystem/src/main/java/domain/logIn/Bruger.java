package domain.logIn;

import peristancy.IDataBruger;

public class Bruger implements IDataBruger {

    private String brugernavn;

    private String adgangskode;

    private String email;

    private Rettighed rettighed;

    private int brugerID;


    public Bruger(String brugernavn, String adgangskode, String email, Rettighed rettighed) {
        this.brugernavn = brugernavn;
        this.adgangskode = adgangskode;
        this.email = email;
        this.rettighed = rettighed;
    }
    public Bruger(){}

    @Override
    public String getBrugerNavn() {
        return null;
    }

    public void setBrugernavn(String brugernavn) {
        this.brugernavn = brugernavn;
    }

    public String getAdgangsKode() {
        return null;
    }

    public void setAdgangskode(String adgangskode) {
        this.adgangskode = adgangskode;
    }

    @Override
    public String getEmail() {
        return null;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Rettighed getRettighed() {
        return rettighed;
    }

    public void setRettighed(Rettighed rettighed) {
        this.rettighed = rettighed;
    }

    @Override
    public int getBrugerID() {
        return 0;
    }

    public void setBrugerID(int brugerID) {
        this.brugerID = brugerID;
    }

}