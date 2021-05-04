package domain.logIn;

import Intefaces.IBruger;


import java.util.List;

public class Bruger implements IBruger {

    private String brugernavn;

    private String adgangskode;

    private String email;

    private Rettighed rettighed;

    private int brugerID;

    private List<Integer> produktionIDer;

    public Bruger(String brugernavn, String adgangskode, String email, Rettighed rettighed, int brugerID) {
        this.brugernavn = brugernavn;
        this.adgangskode = adgangskode;
        this.email = email;
        this.rettighed = rettighed;
        this.brugerID = brugerID;
    }

    public Bruger(){}

    public String getBrugernavn() {
        return brugernavn;
    }

    public void setBrugernavn(String brugernavn) {
        this.brugernavn = brugernavn;
    }

    public String getAdgangskode() {
        return adgangskode;
    }

    public void setAdgangskode(String adgangskode) {
        this.adgangskode = adgangskode;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Rettighed getRettighed() {
        return rettighed;
    }

    @Override
    public List<Integer> getProduktionsIDer() {
        return produktionIDer;
    }

    @Override
    public void setProduktionsIDer(List<Integer> produktionsIDer) {
        this.produktionIDer = produktionsIDer;
    }

    @Override
    public void addProduktionIDer(int produktionsID) {
        this.produktionIDer.add(produktionsID);
    }

    public void setRettighed(Rettighed rettighed) {
        this.rettighed = rettighed;
    }

    @Override
    public int getBrugerID() {
        return brugerID;
    }

    public void setBrugerID(int brugerID) {
        this.brugerID = brugerID;
    }
}