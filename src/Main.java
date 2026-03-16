import java.util.*;

class Reservation {
    String guest;
    String roomType;

    Reservation(String guest, String roomType) {
        this.guest = guest;
        this.roomType = roomType;
    }
}

class BookingSystem {

    Queue<Reservation> queue = new LinkedList<>();
    Map<String, Integer> inventory = new HashMap<>();

    BookingSystem() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
    }

    synchronized void addRequest(Reservation r) {
        queue.add(r);
    }

    synchronized void processBooking() {

        Reservation r = queue.poll();
        if (r == null) return;

        int available = inventory.getOrDefault(r.roomType, 0);

        if (available > 0) {
            inventory.put(r.roomType, available - 1);
            System.out.println(r.guest + " booked " + r.roomType);
        } else {
            System.out.println("No room available for " + r.guest);
        }
    }
}

class BookingThread extends Thread {

    BookingSystem system;

    BookingThread(BookingSystem system) {
        this.system = system;
    }

    public void run() {
        system.processBooking();
    }
}

public class Main {

    public static void main(String[] args) {

        BookingSystem system = new BookingSystem();

        system.addRequest(new Reservation("Aditya", "Single Room"));
        system.addRequest(new Reservation("Rahul", "Single Room"));
        system.addRequest(new Reservation("Priya", "Single Room"));

        Thread t1 = new BookingThread(system);
        Thread t2 = new BookingThread(system);
        Thread t3 = new BookingThread(system);

        t1.start();
        t2.start();
        t3.start();
    }
}