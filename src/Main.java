import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class BookingQueue {
    Queue<Reservation> queue = new LinkedList<>();

    void addRequest(Reservation r) {
        queue.add(r);
    }

    Reservation getNextRequest() {
        return queue.poll();
    }

    boolean isEmpty() {
        return queue.isEmpty();
    }
}

class RoomInventory {
    Map<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    void decrease(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }
}

class BookingService {

    Map<String, Set<String>> allocatedRooms = new HashMap<>();
    Set<String> usedRoomIds = new HashSet<>();

    void processBooking(Reservation r, RoomInventory inventory) {

        int available = inventory.getAvailability(r.roomType);

        if (available <= 0) {
            System.out.println("No rooms available for " + r.roomType);
            return;
        }

        String roomId = r.roomType.replace(" ", "") + "-" + (available);

        if (usedRoomIds.contains(roomId)) {
            System.out.println("Room already allocated");
            return;
        }

        usedRoomIds.add(roomId);

        allocatedRooms.putIfAbsent(r.roomType, new HashSet<>());
        allocatedRooms.get(r.roomType).add(roomId);

        inventory.decrease(r.roomType);

        System.out.println("Reservation confirmed for " + r.guestName);
        System.out.println("Room Assigned: " + roomId);
        System.out.println();
    }
}

public class Main {

    public static void main(String[] args) {

        BookingQueue queue = new BookingQueue();
        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService();

        queue.addRequest(new Reservation("Aditya", "Single Room"));
        queue.addRequest(new Reservation("Rahul", "Double Room"));
        queue.addRequest(new Reservation("Priya", "Single Room"));

        while (!queue.isEmpty()) {
            Reservation r = queue.getNextRequest();
            service.processBooking(r, inventory);
        }
    }
}