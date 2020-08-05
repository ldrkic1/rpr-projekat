package ba.unsa.etf.rpr;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Actor {
    private int id;
    private String firstName;
    private String lastName;
    private String biography;
    private LocalDate bornDate;
    private String image;

    public Actor() {

    }

    public Actor(int id,String firstName, String lastName, String biography, LocalDate bornDate, String image) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
        this.bornDate = bornDate;
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

    public LocalDate getBornDate() {
        return bornDate;
    }

    public void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
