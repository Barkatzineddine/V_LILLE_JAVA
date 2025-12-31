package org.example.model.composite;

import java.util.List;


public class NetworkComposite implements LocationComponent {

    private final List<LocationComponent> stations;

    public NetworkComposite(List<LocationComponent> stations) {
        this.stations = stations;
    }

    @Override
    public void displayStatus() {
        System.out.println("=== Station network ===");
        for (LocationComponent station : stations) {
            station.displayStatus();
        }
    }
}
