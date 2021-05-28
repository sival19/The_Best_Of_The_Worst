package domain.credits;

public enum Genre {
    ACTION("Action"),
    COMEDY("Comedy"),
    ADVENTURE("Adventure"),
    SCIFI("Scifi"),
    ROMANCE("Romance"),
    DRAMA("Drama"),
    CRIME("Crime"),
    FANTASY("Fantasy"),
    THRILLER("Thriller"),
    HORROR("Horror");

    private String genreWord;

    Genre(String genreWord) {
        this.genreWord = genreWord;
    }

    @Override
    public String toString() {
        return genreWord;
    }
}
