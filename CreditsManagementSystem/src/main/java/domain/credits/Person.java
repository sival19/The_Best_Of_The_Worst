package domain.credits;

import Intefaces.IDataPerson;
import domain.creditManagement.CatalogObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Person extends CatalogObject implements IDataPerson {

    private String navn;

    private Date foedselsdato;

    private String nationalitet;

    private int personID;

    private String imagePath;

    public Person(String navn, Date foedselsdato, String nationalitet, int personID) {
        this.navn = navn;
        this.foedselsdato = foedselsdato;
        this.nationalitet = nationalitet;
        this.personID = personID;
    }

    public Person(){}

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

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

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return navn +  ", "+ simpleDateFormat.format(foedselsdato) +
                ", " +nationalitet +
                ", personID=" + personID ;
    }
}