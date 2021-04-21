package Intefaces;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import domain.credits.Rolle;
import domain.logIn.Bruger;

@JsonDeserialize(as = Rolle.class)
public interface IDataRolle extends ICatalogObject{

    public String getRolletype();

    public void setRolletype(String rolletype);

    public int getRolleID();

    public void setRolleID(int rolleID);

    public String getImagePath();

    public void setImagePath(String imagePath);
}
