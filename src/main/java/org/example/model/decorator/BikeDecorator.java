package org.example.model.decorator;

import org.example.model.bike.Bike;

/** Base decorator for bikes and their accessories. */
public abstract class BikeDecorator implements Bike {

    protected final Bike decoratedBike;

    public BikeDecorator(Bike bike) {
        this.decoratedBike = bike;
    }

    @Override
    public double getPrice() {
        return decoratedBike.getPrice();
    }

    @Override
    public String getDescription() {
        return decoratedBike.getDescription();
    }

    @Override
    public boolean isOutOfService() {
        return decoratedBike.isOutOfService();
    }

    @Override
    public void incrementUsage() {
        decoratedBike.incrementUsage();
    }

    @Override
    public String getId() {
        return decoratedBike.getId();
    }

    @Override
    public void displayStatus() {
        decoratedBike.displayStatus();
    }
}
