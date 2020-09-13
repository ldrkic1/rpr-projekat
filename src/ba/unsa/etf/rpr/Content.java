package ba.unsa.etf.rpr;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Content {
    private int id;
    private String title;
    private ArrayList<Genre> genre;
    private int year;
    private String director;
    private ArrayList<Actor> mainActors;
    private String description;
    private double rating;
    private String image;
    private double price;
    private ImageView imageView = new ImageView();
    public Content() {
        mainActors = new ArrayList<>();
        genre = new ArrayList<>();
    }

    public Content(int id,String title, ArrayList<Genre> genre, int year, String director, ArrayList<Actor> mainActors, String description, double rating, String image, double price) throws InvalidUrlException {
        if (title.equals("")) throw new IllegalArgumentException();
        if(!AddActorController.isValid(image)) throw new InvalidUrlException();
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.director = director;
        this.mainActors = mainActors;
        this.description = description;
        this.rating = rating;
        this.image = image;
        this.price = price;
        Image img = new Image(image);
        imageView.setImage(img);
        imageView.setFitHeight(200);
        imageView.setFitWidth(150);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.equals("")) throw new IllegalArgumentException();
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
        if (director.equals("")) throw new IllegalArgumentException();
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
        if (description.equals("")) throw new IllegalArgumentException();
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

    public void setImage(String image) throws InvalidUrlException {
        if(!AddActorController.isValid(image)) throw new InvalidUrlException("Invalid URL");
        this.image = image;
        Image img = new Image(image);
        if(image!=null) {
            imageView.setImage(img);
            imageView.setFitHeight(200);
            imageView.setFitWidth(150);
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getActorsString() {
        String s = "";
        for (int i = 0; i < mainActors.size(); i++) {
            if(i == mainActors.size() - 1) s += mainActors.get(i);
            else s += mainActors.get(i) + "\n";
        }
        return s;
    }
}
