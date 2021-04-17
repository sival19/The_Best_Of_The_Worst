package domain.credits;

import Intefaces.IDataRolle;

public class Rolle implements IDataRolle {

    private String rolletype;

    private int rolleID;

    public Rolle(String rolletype, int rolleId) {

    }

    public Rolle(){};

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
}