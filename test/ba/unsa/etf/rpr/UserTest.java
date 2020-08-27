package ba.unsa.etf.rpr;

import static org.junit.jupiter.api.Assertions.*;
import  org.junit.jupiter.api.Test;
class UserTest {
    @Test
    void constructor1() {
        User user = new User();
        assertNull(user.getFirstName());
        assertNull(user.getLastName());
    }
    @Test
    void constructor2() {
        User user = new User(1,"Lamija","Drkic","ldrkic","ldrkc1",15,0);
        assertTrue(user.getFirstName().equals("Lamija"));
        assertEquals("Drkic", user.getLastName());
    }
    @Test
    void constructor3() {
        User user = new User(1,"Lamija","Drkic","ldrkic","ldrkc1",15,1);
        assertTrue(user.isPrivilege());
    }
    @Test
    void userTest() {
        User user = new User();
        user.setId(1);
        user.setFirstName("Lamija");
        user.setLastName("Drkic");
        user.setUsername("lamka");
        user.setPassword("lamka14");
        user.setRoomNumber(12);
        user.setPrivilege(true);
        assertEquals("lamka", user.getUsername());
        assertNotEquals("lamka", user.getPassword());
        assertEquals(1, user.getId());
        assertEquals(12, user.getRoomNumber());
        assertTrue(user.isPrivilege());
        assertEquals(1, user.getIntegerPrivilege());
        assertEquals("DA", user.getPrivilege());
        user.setPrivilege(false);
        assertEquals(0, user.getIntegerPrivilege());
        assertTrue(user.getPrivilege().equals("NE"));

    }
}