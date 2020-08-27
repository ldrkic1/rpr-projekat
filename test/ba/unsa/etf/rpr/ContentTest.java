package ba.unsa.etf.rpr;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

class ContentTest {

    @Test
    void constructor1() throws InvalidURLException {
        Genre g = new Genre(1, "Akcija");
        ArrayList<Genre> genres = new ArrayList<>();
        genres.add(g);
        Actor a = new Actor(1,"Tolga", "Saritas", "Biografija...", LocalDate.of(1991,5,30),"https://i2.cnnturk.com/i/cnnturk/75/800x400/5e1ae3d117aca93390e41d99");
        ArrayList<Actor> actors = new ArrayList<>();
        actors.add(a);
        Content c = new Content();
        c.setMainActors(actors);
        c.setGenre(genres);
        c.setId(1);
        c.setTitle("Soz");
        c.setPrice(0);
        c.setYear(2017);
        c.setDescription("Opis...");
        c.setDirector("Yagiz Alp Akaydin");
        c.setRating(10);
        assertEquals("Tolga Saritas", c.getMainActors().get(0).toString());
        assertEquals(1, c.getId());
        assertEquals(2017, c.getYear());
    }

    @Test
    void constructor2() {
        Content c = new Content();
        assertTrue(c.getMainActors().isEmpty());
        assertTrue(c.getGenre().isEmpty());
    }

    @Test
    void contentTest1() throws InvalidURLException {
        Content c = new Content();
        Genre g = new Genre(1, "Akcija");
        ArrayList<Genre> genres = new ArrayList<>();
        genres.add(g);
        Actor a = new Actor(1,"Tolga", "Saritas", "Biografija...", LocalDate.of(1991,5,30),"https://i2.cnnturk.com/i/cnnturk/75/800x400/5e1ae3d117aca93390e41d99");
        ArrayList<Actor> actors = new ArrayList<>();
        actors.add(a);
        c.setMainActors(actors);
        c.setGenre(genres);
        c.setId(1);
        c.setTitle("Soz");
        c.setPrice(0);
        c.setYear(2017);
        c.setDescription("Opis...");
        c.setDirector("Yagiz Alp Akaydin");
        c.setRating(10);

        assertTrue(c.getDescription().length() > 0);
        assertFalse(c.getDirector().isEmpty());
        assertEquals(10.0, c.getRating());
        assertEquals(0, c.getPrice());
        assertEquals("Soz", c.getTitle());
        assertEquals(2017, c.getYear());
        assertEquals(1, c.getId());
    }
    @Test
    void contentTest2() throws InvalidURLException {
        Content c = new Content();
        Genre g = new Genre(1, "Akcija");
        ArrayList<Genre> genres = new ArrayList<>();
        genres.add(g);
        Actor a = new Actor(1,"Tolga", "Saritas", "Biografija...", LocalDate.of(1991,5,30),"https://i2.cnnturk.com/i/cnnturk/75/800x400/5e1ae3d117aca93390e41d99");
        ArrayList<Actor> actors = new ArrayList<>();
        actors.add(a);
        c.setMainActors(actors);
        c.setGenre(genres);
        c.setId(1);
        c.setTitle("Soz");
        c.setPrice(0);
        c.setYear(2017);
        c.setDescription("Opis...");
        c.setDirector("Yagiz Alp Akaydin");
        c.setRating(10);
        assertEquals("Tolga Saritas", c.getActorsString());
    }

}