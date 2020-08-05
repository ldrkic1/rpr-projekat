package ba.unsa.etf.rpr;

import java.util.ArrayList;

public class Movie extends Content {
    private int durationMinutes;

    public Movie() {

    }

    public Movie(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public Movie(int id, String title, ArrayList<Genre> genre, int year, String director, ArrayList<Actor> mainActors, String description, double rating, String image, double price, int durationMinutes) {
        super(id, title, genre, year, director, mainActors, description, rating, image, price);
        this.durationMinutes = durationMinutes;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
}
