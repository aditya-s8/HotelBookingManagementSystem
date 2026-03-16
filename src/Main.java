import java.io.*;
import java.util.*;

class Reservation implements Serializable {
    String id;
    String guest;
    String roomType;

    Reservation(String id, String guest, String roomType) {
        this.id = id;
        this.guest = guest;
        this.roomType = roomType;
    }
}

class SystemState implements Serializable {
    Map<String, Integer> inventory;
    List<Reservation> bookings;

    SystemState(Map<String, Integer> inventory, List<Reservation> bookings) {
        this.inventory = inventory;
        this.bookings = bookings;
    }
}

class PersistenceService {

    static void save(SystemState state) {
        try {
            ObjectOutputStream out =
                    new ObjectOutputStream(new FileOutputStream("hotel_state.dat"));
            out.writeObject(state);
            out.close();
            System.out.println("State saved.");
        } catch (Exception e) {
            System.out.println("Save failed.");
        }
    }

    static SystemState load() {
        try {
            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream("hotel_state.dat"));
            SystemState state = (SystemState) in.readObject();
            in.close();
            System.out.println("State restored.");
            return state;
        } catch (Exception e) {
            System.out.println("No previous state found.");
            return null;
        }
    }
}

public class Main {

    public static void main(String[] args) {

        SystemState state = PersistenceService.load();

        Map<String, Integer> inventory;
        List<Reservation> bookings;

        if (state == null) {

            inventory = new HashMap<>();
            inventory.put("Single Room", 2);
            inventory.put("Double Room", 1);

            bookings = new ArrayList<>();

        } else {
            inventory = state.inventory;
            bookings = state.bookings;
        }

        bookings.add(new Reservation("RES201", "Aditya", "Single Room"));

        System.out.println("Bookings:");
        for (Reservation r : bookings) {
            System.out.println(r.id + " " + r.guest + " " + r.roomType);
        }

        PersistenceService.save(new SystemState(inventory, bookings));
    }
}