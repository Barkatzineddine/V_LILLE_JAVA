package org.example.model.composite;

import java.util.List;

public class StationComposite implements LocationComponent {

    private String id;
    private List<LocationComponent> emplacements;

    public StationComposite(String id, List<LocationComponent> emplacements) {
        this.id = id;
        this.emplacements = emplacements;
    }

    @Override
    public void displayStatus() {
        System.out.println("Station " + id + " :");
        for (LocationComponent c : emplacements) {
            c.displayStatus();
        }
    }
}
