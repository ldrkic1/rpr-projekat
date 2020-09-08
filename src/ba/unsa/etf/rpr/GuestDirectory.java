package ba.unsa.etf.rpr;

import java.util.ArrayList;

public class GuestDirectory {
    private ArrayList<User> hotelGuests = new ArrayList<>();

    public GuestDirectory() {

    }

    public GuestDirectory(ArrayList<User> hotelGuests) {
        this.hotelGuests = hotelGuests;
    }

    public ArrayList<User> getHotelGuests() {
        return hotelGuests;
    }

    public void setHotelGuests(ArrayList<User> hotelGuests) {
        this.hotelGuests = hotelGuests;
    }
}
