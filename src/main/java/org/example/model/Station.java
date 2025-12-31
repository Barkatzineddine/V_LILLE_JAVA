package org.example.model;

import java.util.ArrayList;
import java.util.List;

import org.example.model.bike.Bike;
import org.example.model.observer.StationObserver;

/**
 * Represents a bike station in the bike-sharing system.
 *
 * A station has a limited capacity and allows users to deposit
 * and withdraw bikes. It also notifies a control center when
 * important events occur (Observer pattern).
 */

public class Station {

    private final String id;
    private final int capacity;
    private final List<Bike> slots;

    private int emptyStreak = 0;
    private int fullStreak = 0;

    private StationObserver observer;

    public Station(String id, int capacity) {
        if (capacity < 1) throw new IllegalArgumentException("capacity must be >= 1");
        this.id = id;
        this.capacity = capacity;
        this.slots = new ArrayList<>();
    }

    public void setObserver(StationObserver observer) {
        this.observer = observer;
    }

    public String getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public int occupiedSlots() {
        return slots.size();
    }

    public int freeSlots() {
        return capacity - slots.size();
    }

    public boolean isEmpty() {
        return occupiedSlots() == 0;
    }

    public boolean isFull() {
        return freeSlots() == 0;
    }

    /** Deposits a bike if there is space. */
    public boolean deposit(Bike bike) {
        if (bike == null) return false;
        if (isFull()) {
            notifyObserver("Station full, deposit impossible", this);
            return false;
        }
        slots.add(bike);
        notifyObserver("Bike deposited", this);
        return true;
    }

    /** Withdraws a bike from the station (FIFO). */
    public Bike withdraw() {
        if (isEmpty()) {
            notifyObserver("Station empty, withdraw impossible", this);
            return null;
        }
        Bike b = slots.remove(0);
        notifyObserver("Bike withdrawn", this);
        return b;
    }

    /** Called once per simulation step. */
    public void tick() {
        if (isEmpty()) emptyStreak++; else emptyStreak = 0;
        if (isFull()) fullStreak++; else fullStreak = 0;
    }

    public int getEmptyStreak() {
        return emptyStreak;
    }

    public int getFullStreak() {
        return fullStreak;
    }

    private void notifyObserver(String msg, Station station) {
        if (observer != null) observer.notify(msg, station);
    }

    @Override
    public String toString() {
        return "Station{id='" + id + "', bikes=" + occupiedSlots() + "/" + capacity + "}";
    }
}
