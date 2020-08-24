package ba.unsa.etf.rpr;

import java.util.ArrayList;

public class Serial extends Content {
    private int seasonsNumber;
    private int episodesPerSeasonNumber;

    Serial() {

    }
    public Serial(int seasonsNumber, int episodesPerSeasonNumber) {
        this.seasonsNumber = seasonsNumber;
        this.episodesPerSeasonNumber = episodesPerSeasonNumber;
    }

    public Serial(int id, String title, ArrayList<Genre> genre, int year, String director, ArrayList<Actor> mainActors, String description, double rating, String image, double price, int seasonsNumber, int episodesPerSeasonNumber) throws InvalidURLException {
        super(id, title, genre, year, director, mainActors, description, rating, image, price);
        this.seasonsNumber = seasonsNumber;
        this.episodesPerSeasonNumber = episodesPerSeasonNumber;
    }

    public int getSeasonsNumber() {
        return seasonsNumber;
    }

    public void setSeasonsNumber(int seasonsNumber) {
        this.seasonsNumber = seasonsNumber;
    }

    public int getEpisodesPerSeasonNumber() {
        return episodesPerSeasonNumber;
    }

    public void setEpisodesPerSeasonNumber(int episodesPerSeasonNumber) {
        this.episodesPerSeasonNumber = episodesPerSeasonNumber;
    }
}
