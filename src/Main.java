import java.util.HashMap;
import java.util.Map;

abstract class Room {
    String type;
    int beds;
    double price;

    Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    void display() {
        System.out.println("Room: " + type);
        System.out.println("Beds: " + beds);
        System.out.println("Price: $" + price);
    }

    String getType() {
        return type;
    }
}

class SingleRoom extends Room {
    SingleRoom() {
        super("Single Room", 1, 100);
    }
}

class DoubleRoom extends Room {
    DoubleRoom() {
        super("Double Room", 2, 180);
    }
}

class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite Room", 3, 350);
    }
}

class RoomInventory {
    Map<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 0);
    }

    int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }
}

class SearchService {

    RoomInventory inventory;

    SearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    void search(Room[] rooms) {
        System.out.println("\nAvailable Rooms:");

        for (Room r : rooms) {
            int available = inventory.getAvailability(r.getType());

            if (available > 0) {
                r.display();
                System.out.println("Available: " + available);
                System.out.println();
            }
        }
    }
}

public class Main {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        SearchService service = new SearchService(inventory);

        service.search(rooms);
    }
}