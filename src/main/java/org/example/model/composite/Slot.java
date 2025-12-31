package org.example.model.composite;

import org.example.model.bike.Bike;

/** Leaf of the composite: one slot that may contain a bike. */
public class Slot implements LocationComponent {

    private Bike bike;

    public Slot(Bike bike) {
        this.bike = bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    public Bike getBike() {
        return bike;
    }

    @Override
    public void displayStatus() {
        if (bike == null) {
            System.out.println("    * Slot: empty");
        } else {
            bike.displayStatus();
        }
    }
}
