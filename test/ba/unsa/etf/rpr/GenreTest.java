package ba.unsa.etf.rpr;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class GenreTest {

    @Test
    void constructor1() {
        Genre g = new Genre(1, "Test");
        assertEquals(1, g.getId());
        assertEquals("Test", g.getName());
    }
    @Test
    void constructor2() {
        Genre g = new Genre();
        assertNull(g.getName());
    }
    @Test
    void genreTest() {
        Genre g = new Genre();
        g.setId(2);
        g.setName("Akcija");
        assertEquals(2, g.getId());
        assertEquals("Akcija", g.getName());
        assertEquals("Akcija", g.toString());
        assertThrows(IllegalArgumentException.class, () -> g.setName(""));
    }
}