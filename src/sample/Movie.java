package sample;

import java.util.ArrayList;

public class Movie {
    private String title;
    private String genre;
    private int year;
    private String director;
    private ArrayList<String> mainActors = new ArrayList<>();
    private String description;
    private int durationMinutes;

    public Movie() { }
    public Movie(String title, String genre, int year, String director, ArrayList<String> mainActors, String description, int durationMinutes) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.director = director;
        this.mainActors = mainActors;
        this.description = description;
        this.durationMinutes = durationMinutes;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public ArrayList<String> getMainActors() {
        return mainActors;
    }

    public void setMainActors(ArrayList<String> mainActors) {
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
}
