package domain.credits;

import java.util.Date;
import java.util.List;

public class Program {

    private String programNavn;

    private int produktionsID;

    private Date udgivelsesDato;

    private ProgramType programType;

    private String genre;

    private double længde;

    private int listeAfCredits;

    public Program(String programNavn, String produktionsID, String udgivelsesDato, ProgramType programType, String genre, double længde, List<Credit> listeAfCredits) {

    }

}