package Intefaces;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import domain.credits.Person;
import domain.logIn.Bruger;

import java.util.Date;

@JsonDeserialize(as = Person.class)
public interface IDataPerson extends ICatalogObject {
    public String getNavn();

    public void setNavn(String navn);

    public Date getFoedselsdato();

    public void setFoedselsdato(Date foedselsdato);

    public String getNationalitet();

    public void setNationalitet(String nationalitet);

    public int getPersonID();

    public void setPersonID(int personID);

    public String getImagePath();

    public void setImagePath(String imagePath);


}
