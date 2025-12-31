package org.example.model.decorator;

import org.example.model.bike.Bike;

/**
 * Decorator that adds a basket to a bike.
 *
 * This decorator increases the rental price of the bike.
 */
public class BasketDecorator extends BikeDecorator {

    public BasketDecorator(Bike bike) {
        super(bike);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 1.0;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Basket";
    }
}
