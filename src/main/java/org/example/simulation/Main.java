package org.example.simulation;

import java.util.List;
import java.util.Random;

import org.example.model.Station;
import org.example.model.User;
import org.example.model.bike.Bike;
import org.example.model.bike.ClassicBike;
import org.example.model.bike.ElectricBike;
import org.example.model.decorator.BasketDecorator;
import org.example.model.decorator.LuggageRackDecorator;
import org.example.service.ControlCenter;
import org.example.service.strategy.SimpleRedistributionStrategy;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("========== BIKE SHARING SIMULATION ==========");

        // 1) Stations
        Station s1 = new Station("S1", 10);
        Station s2 = new Station("S2", 12);
        Station s3 = new Station("S3", 15);
        List<Station> stations = List.of(s1, s2, s3);

        // 2) Control center (Singleton + Observer + Strategy)
        ControlCenter center = ControlCenter.getInstance();
        center.registerStations(stations);
        center.setStrategy(new SimpleRedistributionStrategy());

        s1.setObserver(center);
        s2.setObserver(center);
        s3.setObserver(center);

        // 3) Initial bikes (Decorator)
        depositInitialBikes(s1, s2, s3);

        // 4) Users
        User u1 = new User("Alice", 12);
        User u2 = new User("Bob", 4);
        List<User> users = List.of(u1, u2);

        // 5) Simulation loop (compact output)
        Random rng = new Random();

        for (int t = 0; t < 10; t++) {
            System.out.println("\n========== STEP " + t + " ==========");

            stations.forEach(Station::tick);

            for (User u : users) simulateUserAction(u, stations, rng);

            center.tick(stations);

            System.out.println("\n--- STATION STATUS ---");
            for (Station st : stations) {
                System.out.printf("%s : %d/%d occupied | %d free%n",
                        st.getId(), st.occupiedSlots(), st.getCapacity(), st.freeSlots());
            }

            Thread.sleep(300);
        }

        System.out.println("\n========== END ==========");
    }

    private static void depositInitialBikes(Station s1, Station s2, Station s3) {
        s1.deposit(new ClassicBike("C1", 1.0, "Classic Bike"));
        s1.deposit(new BasketDecorator(new ClassicBike("C2", 1.0, "Classic Bike")));

        s2.deposit(new ElectricBike("E1", 2.0, "Electric Bike"));
        s2.deposit(new LuggageRackDecorator(new ClassicBike("C3", 1.0, "Classic Bike")));

        Bike full = new BasketDecorator(new LuggageRackDecorator(new ElectricBike("E2", 2.0, "Electric Bike")));
        s3.deposit(full);
    }

    private static void simulateUserAction(User u, List<Station> stations, Random rng) {
        Station st = stations.get(rng.nextInt(stations.size()));

        if (rng.nextBoolean()) {
            boolean ok = u.rentBike(st);
            System.out.println("• " + u.getName() + " tries to rent at " + st.getId() + " -> " + (ok ? "SUCCESS" : "FAIL"));
        } else {
            boolean ok = u.returnBike(st);
            System.out.println("• " + u.getName() + " tries to return at " + st.getId() + " -> " + (ok ? "SUCCESS" : "FAIL"));
        }
    }
}
