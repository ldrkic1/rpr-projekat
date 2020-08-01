package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.Date;

public class Actor {
    private String firstName;
    private String lastName;
    private String biography;
    private Date bornDate;
    private ArrayList<Movie> filmography;
    private String image;

    public Actor() {
        filmography = new ArrayList<>();
    }

    public Actor(String firstName, String lastName, String biography, Date bornDate, ArrayList<Movie> filmography, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
        this.bornDate = bornDate;
        this.filmography = filmography;
        this.image = image;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public ArrayList<Movie> getFilmography() {
        return filmography;
    }

    public void setFilmography(ArrayList<Movie> filmography) {
        this.filmography = filmography;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void addMovie(Movie movie) {
        filmography.add(movie);
    }
}
