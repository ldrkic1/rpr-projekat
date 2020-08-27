package ba.unsa.etf.rpr;

import static org.junit.jupiter.api.Assertions.*;
import  org.junit.jupiter.api.Test;
class RequestTest {
    @Test
    void constructor1() {
        Request request = new Request();
        assertNull(request.getUser());
        assertNull(request.getContent());
    }
    @Test
    void constructor2() {
        Serial s = new Serial();;
        s.setId(1);
        s.setTitle("Soz");
        s.setPrice(0);
        s.setYear(2017);
        s.setDescription("Opis...");
        s.setDirector("Yagiz Alp Akaydin");
        s.setRating(10);
        s.setSeasonsNumber(3);
        s.setEpisodesNumber(84);
        Content content = s;
        User user = new User(1,"Lamija","Drkic","ldrkic","ldrkc1",15,0);
        Request request = new Request(1, user, content);
        assertNotNull(request.getUser());
        assertNotNull(request.getContent());
    }
    @Test
    void requestTest() {
        Serial s = new Serial();;
        s.setId(1);
        s.setTitle("Soz");
        s.setPrice(0);
        s.setYear(2017);
        s.setDescription("Opis...");
        s.setDirector("Yagiz Alp Akaydin");
        s.setRating(10);
        s.setSeasonsNumber(3);
        s.setEpisodesNumber(84);
        Content content = s;
        User user = new User(1,"Lamija","Drkic","ldrkic","ldrkc1",15,0);
        Request request = new Request();
        request.setId(11);
        request.setUser(user);
        request.setContent(content);
        assertEquals(11, request.getId());
    }
}