package ba.unsa.etf.rpr;

import static org.junit.jupiter.api.Assertions.*;
import  org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

class MovieTest {

    @Test
    void constructor1() {
        Movie m = new Movie();
        assertNull(m.getTitle());
    }
    @Test
    void constructor2() throws InvalidURLException {
        ArrayList<Genre> genres = new ArrayList<>();
        genres.add(new Genre(1, "Romantika"));
        genres.add(new Genre(2, "Drama"));
        ArrayList<Actor> actors = new ArrayList<>();
        actors.add(new Actor(1,"Scott","Eastwood","Biografija", LocalDate.of(1983,3,21),"https://i.pinimg.com/564x/22/a9/aa/22a9aaad797de4acd63c6d2ffc37f3b1.jpg"));
        actors.add(new Actor(2, "Britt","Robertson", "Biografija..", LocalDate.of(1990,4,18),"https://i.pinimg.com/564x/75/45/17/754517a5c2f0fefc23a071acd401bef1.jpg"));
        Movie m = new Movie();
        m.setId(1);
        m.setTitle("The Longest Ride");
        m.setGenre(genres);
        m.setMainActors(actors);
        m.setDurationMinutes(123);
        assertNotNull(m.getTitle());
        assertEquals(1, m.getId());
        assertTrue(m.getMainActors().size() == 2);
        assertEquals(2, m.getGenre().size());
        assertFalse(m.getDurationMinutes() == 124);
    }
    @Test
    void constructor3() {
        Movie m = new Movie(123);
        assertEquals(123, m.getDurationMinutes());
    }
    @Test
    void constructor4() {
        assertThrows(InvalidURLException.class, () -> {
            Movie m = new Movie(1, "Test",null, 2002, "Test", null, "Testtt", 5, "link",12,123);
        });
    }
    @Test
    void movieTest() {
        Movie m = new Movie();
        m.setYear(2015);
        m.setPrice(0);
        m.setRating(10);
        m.setDirector("Opis..");
        m.setDirector("George Tillman Jr.");
        assertEquals(2015, m.getYear());
        assertTrue(m.getPrice() == 0);
        assertFalse(m.getRating() < 10);
        assertEquals("George", m.getDirector().substring(0,6));
        assertNull(m.getImage());
    }
}