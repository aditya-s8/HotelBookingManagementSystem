import java.util.*;

class Service {
    String name;
    double price;

    Service(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class AddOnServiceManager {

    Map<String, List<Service>> reservationServices = new HashMap<>();

    void addService(String reservationId, Service service) {

        reservationServices.putIfAbsent(reservationId, new ArrayList<>());
        reservationServices.get(reservationId).add(service);
    }

    double calculateCost(String reservationId) {

        double total = 0;

        List<Service> services = reservationServices.get(reservationId);

        if (services != null) {
            for (Service s : services) {
                total += s.price;
            }
        }

        return total;
    }

    void showServices(String reservationId) {

        List<Service> services = reservationServices.get(reservationId);

        if (services == null) {
            System.out.println("No services selected.");
            return;
        }

        System.out.println("Services for reservation " + reservationId + ":");

        for (Service s : services) {
            System.out.println("- " + s.name + " ($" + s.price + ")");
        }

        System.out.println("Total Add-On Cost: $" + calculateCost(reservationId));
    }
}

public class Main {

    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "RES-101";

        manager.addService(reservationId, new Service("Breakfast", 20));
        manager.addService(reservationId, new Service("Airport Pickup", 40));
        manager.addService(reservationId, new Service("Spa Access", 60));

        manager.showServices(reservationId);
    }
}