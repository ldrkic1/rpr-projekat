package ba.unsa.etf.rpr;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class EmployeeTest {

    @Test
    void constructor1() {
        Employee employee = new Employee();
        assertNull(employee.getUsername());
    }
    @Test
    void constructor2() {
        Employee employee = new Employee(1,"employee","password");
        assertNotNull(employee.getUsername());
        assertNotNull(employee.getPassword());
        assertEquals(1, employee.getId());
    }
    @Test
    void emplyeeTest() {
        Employee employee = new Employee();
        employee.setUsername("user");
        employee.setPassword("password");
        employee.setId(14);
        assertEquals("user", employee.getUsername());
        assertTrue(employee.getPassword().equals("password"));
    }
}