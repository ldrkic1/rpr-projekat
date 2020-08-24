package ba.unsa.etf.rpr;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
class ActorTest {

    @Test
    void constructor1() throws InvalidURLException {
        Actor a = new Actor(1,"Tolga", "Saritas", "Biografija...", LocalDate.of(1991,5,30),"https://i2.cnnturk.com/i/cnnturk/75/800x400/5e1ae3d117aca93390e41d99");
        assertEquals("Tolga", a.getFirstName());
        assertEquals(30, a.getBornDate().getDayOfMonth());
    }

    @Test
    void constructor2() {
        Actor a = new Actor();
        assertNull(a.getFirstName());
    }

    @Test
    void setFirstNameTest() {
        Actor a = new Actor();
        a.setFirstName("Niko");
        assertEquals("Niko", a.getFirstName());
    }

    @Test
    void setLastNameTest() {
        Actor a = new Actor();
        a.setLastName("Nikic");
        assertEquals("Nikic", a.getLastName());
    }

    @Test
    void imageURLTest1() throws InvalidURLException {
        Actor a = new Actor();
        assertThrows(InvalidURLException.class, () -> a.setImage("neispravan link"));
    }
    @Test
    void imageURLTest2() throws InvalidURLException {
        Actor a = new Actor();
        assertDoesNotThrow(() -> a.setImage("https://www.turkishdrama.com/wp-content/uploads/2017/01/Hande-Ercel-Actress.jpg"));
        assertFalse(a.getImage().isEmpty());
    }
    @Test
    void toStringTest() {
        Actor a = new Actor();
        a.setId(12);
        a.setFirstName("Lamija");
        a.setLastName("Drkic");
        assertEquals("Lamija Drkic", a.toString());
    }

    @Test
    void actorTest() {
        Actor a = new Actor();
        a.setId(1921);
        a.setFirstName("Niko");
        a.setLastName("Nekic");
        a.setBornDate(LocalDate.of(1921, 10, 13));
        a.setBiography("Biografija...");
        assertEquals(1921, a.getBornDate().getYear());
        assertTrue(!a.getBiography().isEmpty());
        assertEquals(1921, a.getId());
        Actor b = new Actor();
        b.setFirstName("Niko");
        b.setLastName("Nekic");
        b.setBiography("Biografija...");
        b.setBornDate(LocalDate.of(1921, 10, 6));
        assertFalse(a.equals(b));
    }
}