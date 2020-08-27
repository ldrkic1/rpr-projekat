package ba.unsa.etf.rpr;
import java.util.ArrayList;
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private int roomNumber;
    private boolean privilege;
    public User() {
    }

    public User(int id, String firstName, String lastName, String username, String password, int roomNumber, int privilege) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.roomNumber = roomNumber;
        if(privilege == 0) this.privilege = false;
        else this.privilege = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean isPrivilege() {
        return privilege;
    }

    public void setPrivilege(boolean privilege) {
        this.privilege = privilege;
    }

    public String getPrivilege() {
        if(privilege) return "DA";
        return "NE";
    }
    public int getIntegerPrivilege() {
        if(privilege) return 1;
        return 0;
    }
}
