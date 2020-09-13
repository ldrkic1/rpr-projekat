package ba.unsa.etf.rpr;

import java.util.ArrayList;

public class Serial extends Content {
    private int seasonsNumber;
    private int episodesNumber;

    Serial() {

    }
    public Serial(int seasonsNumber, int episodesNumber) {
        this.seasonsNumber = seasonsNumber;
        this.episodesNumber = episodesNumber;
    }

    public Serial(int id, String title, ArrayList<Genre> genre, int year, String director, ArrayList<Actor> mainActors, String description, double rating, String image, double price, int seasonsNumber, int episodesNumber) throws InvalidUrlException {
        super(id, title, genre, year, director, mainActors, description, rating, image, price);
        this.seasonsNumber = seasonsNumber;
        this.episodesNumber = episodesNumber;
    }

    public int getSeasonsNumber() {
        return seasonsNumber;
    }

    public void setSeasonsNumber(int seasonsNumber) {
        this.seasonsNumber = seasonsNumber;
    }

    public int getEpisodesNumber() {
        return episodesNumber;
    }

    public void setEpisodesNumber(int episodesNumber) {
        this.episodesNumber = episodesNumber;
    }
}
