package domain.credits;

public enum ProgramType {
    FILM("Film"),
    SERIE("Serie"),
    DOKUMENTAR("Dokumentar"),
    KORTFILM("KortFilm");

    private String programTypeWord;

    ProgramType(String programTypeWord){ this.programTypeWord = programTypeWord;}

    @Override
    public String toString() {
        return programTypeWord;
    }
}
