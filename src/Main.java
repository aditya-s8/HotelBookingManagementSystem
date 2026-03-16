import java.util.LinkedList;
import java.util.Queue;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void display() {
        System.out.println(guestName + " requested " + roomType);
    }
}

class BookingQueue {

    Queue<Reservation> queue = new LinkedList<>();

    void addRequest(Reservation r) {
        queue.add(r);
    }

    void showQueue() {
        System.out.println("\nBooking Requests in Queue:");

        for (Reservation r : queue) {
            r.display();
        }
    }
}

public class Main {

    public static void main(String[] args) {

        BookingQueue bookingQueue = new BookingQueue();

        bookingQueue.addRequest(new Reservation("Aditya", "Single Room"));
        bookingQueue.addRequest(new Reservation("Rahul", "Double Room"));
        bookingQueue.addRequest(new Reservation("Priya", "Suite Room"));

        bookingQueue.showQueue();
    }
}