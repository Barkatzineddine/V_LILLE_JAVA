package org.example.model.composite;

import java.util.List;

/** Composite node representing one station for display purposes. */
public class StationComposite implements LocationComponent {

    private final String id;
    private final List<LocationComponent> slots;

    public StationComposite(String id, List<LocationComponent> slots) {
        this.id = id;
        this.slots = slots;
    }

    @Override
    public void displayStatus() {
        System.out.println("Station " + id + ":");
        for (LocationComponent c : slots) {
            c.displayStatus();
        }
    }
}
