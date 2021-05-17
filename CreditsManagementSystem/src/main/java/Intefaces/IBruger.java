package Intefaces;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import domain.logIn.Bruger;
import domain.logIn.Rettighed;

import java.util.List;

@JsonDeserialize(as = Bruger.class)
public interface IBruger {
    String getBrugernavn();
    void setBrugerNavn(String brugerNavn);
    String getAdgangskode();
    void setAdgangskode(String adgangskode);
    String getEmail();
    void setEmail(String email);
    int getBrugerID();
    void setBrugerID(int brugerID);
    Rettighed getRettighed();
    void setRettighed( String rettighed);
    List<Integer> getProduktionsIDer();
    void setProduktionsIDer(List<Integer> produktionsIDer);

}
