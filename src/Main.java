import java.util.*;

class Reservation {
    String reservationId;
    String guestName;
    String roomType;

    Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class BookingHistory {

    List<Reservation> history = new ArrayList<>();

    void addReservation(Reservation r) {
        history.add(r);
    }

    List<Reservation> getHistory() {
        return history;
    }
}

class BookingReportService {

    void showAllBookings(List<Reservation> history) {

        System.out.println("\nBooking History:");

        for (Reservation r : history) {
            System.out.println(
                    r.reservationId + " | " +
                            r.guestName + " | " +
                            r.roomType
            );
        }
    }

    void showSummary(List<Reservation> history) {

        Map<String, Integer> count = new HashMap<>();

        for (Reservation r : history) {
            count.put(r.roomType, count.getOrDefault(r.roomType, 0) + 1);
        }

        System.out.println("\nBooking Summary:");

        for (String type : count.keySet()) {
            System.out.println(type + " bookings: " + count.get(type));
        }
    }
}

public class Main {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        history.addReservation(new Reservation("RES-101", "Aditya", "Single Room"));
        history.addReservation(new Reservation("RES-102", "Rahul", "Double Room"));
        history.addReservation(new Reservation("RES-103", "Priya", "Single Room"));

        BookingReportService report = new BookingReportService();

        report.showAllBookings(history.getHistory());
        report.showSummary(history.getHistory());
    }
}