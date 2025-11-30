package org.example.model.composite;

import java.util.List;

public class NetworkComposite implements LocationComponent {

    private List<LocationComponent> stations;

    public NetworkComposite(List<LocationComponent> stations) {
        this.stations = stations;
    }

    @Override
    public void displayStatus() {
        System.out.println("=== Réseau des stations ===");
        for (LocationComponent station : stations) {
            station.displayStatus();
        }
    }
}
