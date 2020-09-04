package ba.unsa.etf.rpr;

import static org.junit.jupiter.api.Assertions.*;
import  org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

class SerialTest {
    @Test
    void constructor1() {
        Serial s = new Serial();
        assertNull(s.getTitle());
    }
    @Test
    void constructor2() {
        Serial s = new Serial(3,84);
        assertEquals(3, s.getSeasonsNumber());
        assertEquals(84, s.getEpisodesNumber());
    }
    @Test
    void constructor3() {
        assertThrows(InvalidURLException.class, () -> {
            Serial m = new Serial(1, "Test",null, 2002, "Test", null, "Testtt", 5, "link",12,1,30);
        });
    }
    @Test
    void serialTest() throws InvalidURLException {
        Genre g = new Genre(1, "Akcija");
        ArrayList<Genre> genres = new ArrayList<>();
        genres.add(g);
        Actor a = new Actor(1,"Tolga", "Saritas", "Biografija...", LocalDate.of(1991,5,30),"https://i2.cnnturk.com/i/cnnturk/75/800x400/5e1ae3d117aca93390e41d99");
        ArrayList<Actor> actors = new ArrayList<>();
        actors.add(a);
        Serial s = new Serial();
        s.setMainActors(actors);
        s.setGenre(genres);
        s.setId(1);
        s.setTitle("Soz");
        s.setPrice(0);
        s.setYear(2017);
        s.setDescription("Opis...");
        s.setDirector("Yagiz Alp Akaydin");
        s.setRating(10);
        s.setSeasonsNumber(3);
        s.setEpisodesNumber(84);
        assertEquals(1, s.getId());
        assertEquals(2017, s.getYear());
        assertEquals(3, s.getTitle().length());
        assertNull(s.getImage());
        assertEquals("Yagiz", s.getDirector().substring(0,5));
        assertFalse(s.getDescription().equals(""));
    }

}