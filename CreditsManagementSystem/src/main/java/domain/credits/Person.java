package domain.credits;

import Intefaces.IDataPerson;
import javafx.scene.image.Image;

import java.util.Date;

public class Person implements IDataPerson {

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
        return
                navn + "\n" +
                "Født: " + foedselsdato + "\n" +
                "Nationalitet: " + nationalitet + "\n" +
                "PersonID: " + personID
                ;
    }
}