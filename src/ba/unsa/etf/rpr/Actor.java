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

    public Actor(int id,String firstName, String lastName, String biography, LocalDate bornDate, String image) throws InvalidURLException {
        if (firstName.equals("") || lastName.equals("")) throw new IllegalArgumentException();
        if (bornDate == null) throw new NullPointerException();
        if (!AddActorController.isValid(image)) throw new InvalidURLException();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
        this.bornDate = bornDate;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName.equals("")) throw new IllegalArgumentException();
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName.equals("")) throw new IllegalArgumentException();
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
        if(bornDate == null) throw new NullPointerException();
        this.bornDate = bornDate;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) throws InvalidURLException {
        if(!AddActorController.isValid(image)) throw new InvalidURLException();
        this.image = image;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object obj) {
        Actor a = (Actor) obj;
        if(a == null) return false;
        return a.getFirstName().equals(firstName) && a.getLastName().equals(lastName) && a.getBiography().equals(biography) && a.getBornDate().equals(bornDate);
    }
}