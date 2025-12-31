package org.example.model.bike;

import org.example.model.composite.LocationComponent;


public interface Bike extends LocationComponent{

    double getPrice();
    String getDescription();
    boolean isOutOfService();
    void incrementUsage();
    String getId();
}
