import java.util.*;

class Reservation {
    String reservationId;
    String roomType;
    String roomId;

    Reservation(String reservationId, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.roomType = roomType;
        this.roomId = roomId;
    }
}

class RoomInventory {

    Map<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
    }

    void increase(String roomType) {
        inventory.put(roomType, inventory.get(roomType) + 1);
    }

    void showInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }
    }
}

class CancellationService {

    Map<String, Reservation> bookings;
    Stack<String> releasedRooms = new Stack<>();

    CancellationService(Map<String, Reservation> bookings) {
        this.bookings = bookings;
    }

    void cancel(String reservationId, RoomInventory inventory) {

        if (!bookings.containsKey(reservationId)) {
            System.out.println("Cancellation failed: reservation not found");
            return;
        }

        Reservation r = bookings.get(reservationId);

        releasedRooms.push(r.roomId);
        inventory.increase(r.roomType);

        bookings.remove(reservationId);

        System.out.println("Reservation cancelled: " + reservationId);
        System.out.println("Room released: " + r.roomId);
    }
}

public class Main {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Map<String, Reservation> bookings = new HashMap<>();

        bookings.put("RES101", new Reservation("RES101", "Single Room", "S1"));
        bookings.put("RES102", new Reservation("RES102", "Double Room", "D1"));

        CancellationService cancelService = new CancellationService(bookings);

        cancelService.cancel("RES101", inventory);

        inventory.showInventory();
    }
}