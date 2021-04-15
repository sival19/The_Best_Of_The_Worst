package peristancy;

import domain.logIn.Rettighed;

public interface IDataBruger {
    String getBrugerNavn();
    String getAdgangsKode();
    String getEmail();
    int getBrugerID();
    Rettighed getRettighed();


}
