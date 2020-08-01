package ba.unsa.etf.rpr;

import java.util.ArrayList;

public class Movie {
    private String title;
    private ArrayList<String> genre;
    private int year;
    private String director;
    private String writer;
    private ArrayList<Actor> mainActors;
    private String description;
    private int durationMinutes;
    private double rating;
    private String image;

    public Movie() {
        mainActors = new ArrayList<>();
    }

    public Movie(String title, ArrayList<String> genre, int year, String director, String writer, ArrayList<Actor> mainActors, String description, int durationMinutes, double rating, String image) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.director = director;
        this.writer = writer;
        this.mainActors = mainActors;
        this.description = description;
        this.durationMinutes = durationMinutes;
        this.rating = rating;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public ArrayList<Actor> getMainActors() {
        return mainActors;
    }

    public void setMainActors(ArrayList<Actor> mainActors) {
        this.mainActors = mainActors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void addActor(Actor actor) {
        mainActors.add(actor);
    }
}
