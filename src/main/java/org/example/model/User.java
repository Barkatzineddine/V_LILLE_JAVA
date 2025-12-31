package org.example.model;

import org.example.model.bike.Bike;

/**
 * Represents a user of the bike-sharing system.
 *
 * A user can rent and return bikes and has a limited balance.
 */

public class User {

    private final String name;
    private double balance;
    private Bike currentBike;

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }
/**
 * Attempts to rent a bike from the given station.
 *
 * @param station the station from which the bike is rented
 * @return true if the bike was successfully rented, false otherwise
 */
    public boolean rentBike(Station station) {
        if (station == null) return false;
        if (currentBike != null) return false;

        Bike bike = station.withdraw();
        if (bike == null) return false;

        if (bike.isOutOfService()) {
            station.deposit(bike);
            return false;
        }

        double price = bike.getPrice();
        if (balance < price) {
            station.deposit(bike);
            return false;
        }

        balance -= price;
        bike.incrementUsage();
        currentBike = bike;
        return true;
    }

    public boolean returnBike(Station station) {
        if (station == null) return false;
        if (currentBike == null) return false;

        boolean ok = station.deposit(currentBike);
        if (ok) currentBike = null;
        return ok;
    }
}
