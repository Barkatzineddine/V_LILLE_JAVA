package org.example.model.decorator;

import org.example.model.bike.Bike;

/** Adds a luggage rack accessory to a bike. */
public class LuggageRackDecorator extends BikeDecorator {

    public LuggageRackDecorator(Bike bike) {
        super(bike);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 0.5;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Luggage Rack";
    }
}
