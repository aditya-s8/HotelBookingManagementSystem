import java.util.HashMap;
import java.util.Map;

/**
 * Hotel Booking Management System
 * UC3 - Centralized Room Inventory using HashMap
 * <p>
 * Demonstrates how HashMap can be used to maintain
 * a single source of truth for room availability.
 *
 * @author Aditya Raj Singh
 * @version 1.0
 */

// RoomInventory class manages availability
class RoomInventory {

    private final Map<String, Integer> inventory;

    // Constructor initializes inventory
    public RoomInventory() {

        inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Get availability of a room type
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability
    public void updateAvailability(String roomType, int newCount) {
        inventory.put(roomType, newCount);
    }

    // Display full inventory
    public void displayInventory() {

        System.out.println("\nCurrent Room Inventory:");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue() + " rooms available");
        }
    }
}

// Main application
public class Main {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Hotel Booking Management System");
        System.out.println("=================================");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display inventory
        inventory.displayInventory();

        // Example: update availability
        System.out.println("\nUpdating availability for Single Room...");

        inventory.updateAvailability("Single Room", 4);

        // Display updated inventory
        inventory.displayInventory();

        System.out.println("\nApplication terminated.");
    }
}