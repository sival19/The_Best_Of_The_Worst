package domain.credits;

import Intefaces.IDataRolle;
import domain.creditManagement.CatalogObject;
import javafx.scene.image.Image;

public class Rolle extends CatalogObject implements IDataRolle {

    private String rolletype;

    private int rolleID;

    private String imagePath;

    public Rolle(String rolletype, int rolleID) {
        this.rolletype = rolletype;
        this.rolleID = rolleID;
    }


    public Rolle(){}

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getRolletype() {
        return rolletype;
    }

    public void setRolletype(String rolletype) {
        this.rolletype = rolletype;
    }

    public int getRolleID() {
        return rolleID;
    }

    public void setRolleID(int rolleID) {
        this.rolleID = rolleID;
    }

    @Override
    public String toString() {
        return   rolletype + "\n" +
                "rolleID: " + rolleID;
    }
}