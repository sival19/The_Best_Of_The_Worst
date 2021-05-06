package Intefaces;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import domain.logIn.Bruger;
import domain.logIn.Rettighed;

import java.util.List;

@JsonDeserialize(as = Bruger.class)
public interface IBruger {
    String getBrugernavn();
    String getAdgangskode();
    String getEmail();
    int getBrugerID();
    Rettighed getRettighed();
    List<Integer> getProduktionsIDer();
    void setProduktionsIDer(List<Integer> produktionsIDer);

    void addProduktionIDer(int produktionsID);



}
