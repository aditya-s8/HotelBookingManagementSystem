/**
 * Hotel Booking Management System
 * <p>
 * Demonstrates object modeling using abstraction and inheritance.
 * Different room types extend a common abstract Room class.
 *
 * @author Aditya Raj Singh
 * @version 1.0
 */

// Abstract Room class
abstract class Room {

    protected String roomType;
    protected int beds;
    protected double price;

    public Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + beds);
        System.out.println("Price per night: $" + price);
    }
}

// Single Room class
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 100.0);
    }
}

// Double Room class
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 180.0);
    }
}

// Suite Room class
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 350.0);
    }
}

// Main application class
public class Main {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("   Hotel Booking Management System");
        System.out.println("=================================");

        // Create room objects (Polymorphism)
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Static availability variables
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        // Display room information
        System.out.println("\nSingle Room Details:");
        singleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + singleAvailable);

        System.out.println("\nDouble Room Details:");
        doubleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + doubleAvailable);

        System.out.println("\nSuite Room Details:");
        suiteRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + suiteAvailable);

        System.out.println("\nApplication terminated successfully.");
    }
}