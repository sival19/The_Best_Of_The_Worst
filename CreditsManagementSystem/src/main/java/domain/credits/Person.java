package domain.credits;

import Intefaces.IDataPerson;

import java.util.Date;

public class Person implements IDataPerson {

    private String navn;

    private Date foedselsdato;

    private String nationalitet;

    private int personID;

    public Person(String navn, Date foedselsdato, String nationalitet, int personID) {

    }

    public Person(){}

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Date getFoedselsdato() {
        return foedselsdato;
    }

    public void setFoedselsdato(Date foedselsdato) {
        this.foedselsdato = foedselsdato;
    }

    public String getNationalitet() {
        return nationalitet;
    }

    public void setNationalitet(String nationalitet) {
        this.nationalitet = nationalitet;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }
}