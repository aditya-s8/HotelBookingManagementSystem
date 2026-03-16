import java.util.*;

class InvalidBookingException extends Exception {
    InvalidBookingException(String message) {
        super(message);
    }
}

class RoomInventory {

    Map<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
    }

    void validateRoom(String roomType) throws InvalidBookingException {

        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        if (inventory.get(roomType) <= 0) {
            throw new InvalidBookingException("No rooms available for " + roomType);
        }
    }

    void allocateRoom(String roomType) throws InvalidBookingException {

        validateRoom(roomType);

        int available = inventory.get(roomType);

        if (available - 1 < 0) {
            throw new InvalidBookingException("Inventory cannot go negative");
        }

        inventory.put(roomType, available - 1);
    }
}

public class Main {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        String[] bookingRequests = {
                "Single Room",
                "Suite Room",      // invalid type
                "Double Room",
                "Double Room"      // no availability
        };

        for (String request : bookingRequests) {

            try {

                inventory.allocateRoom(request);
                System.out.println("Booking confirmed for " + request);

            } catch (InvalidBookingException e) {

                System.out.println("Booking failed: " + e.getMessage());

            }
        }
    }
}