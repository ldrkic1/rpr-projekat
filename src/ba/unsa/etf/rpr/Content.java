package ba.unsa.etf.rpr;

import java.util.ArrayList;

public class Content {
    private String title;
    private ArrayList<Genre> genre;
    private int year;
    private String director;
    private ArrayList<Actor> mainActors;
    private String description;
    private double rating;
    private String image;

    public Content() {

    }

    public Content(String title, ArrayList<Genre> genre, int year, String director, ArrayList<Actor> mainActors, String description, double rating, String image) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.director = director;
        this.mainActors = mainActors;
        this.description = description;
        this.rating = rating;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Genre> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<Genre> genre) {
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
}
