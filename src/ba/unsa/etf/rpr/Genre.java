package ba.unsa.etf.rpr;

public class Genre {
    private int id;
    private String name;

    public Genre() {
    }

    public Genre(int id, String name) {
        if(name.equals("")) throw new IllegalArgumentException();
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.equals("")) throw new IllegalArgumentException();
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
