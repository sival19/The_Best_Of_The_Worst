package domain.credits;

public enum ProgramType {
    FILM("Film"),
    SERIE("Serie"),
    DOKUMENTAR("Dokumentar"),
    KORTFILM("KortFilm");

    private String programTypeOrd;

    ProgramType(String programTypeOrd){ this.programTypeOrd = programTypeOrd;}

    @Override
    public String toString() {
        return programTypeOrd;
    }
}
