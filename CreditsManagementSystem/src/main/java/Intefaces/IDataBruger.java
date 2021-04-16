package Intefaces;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import domain.logIn.Bruger;
import domain.logIn.Rettighed;

@JsonDeserialize(as = Bruger.class)
public interface IDataBruger {
    String getBrugernavn();
    String getAdgangskode();
    String getEmail();
    int getBrugerID();
    Rettighed getRettighed();


}
