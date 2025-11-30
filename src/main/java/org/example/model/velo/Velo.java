package org.example.model.velo;

import org.example.model.composite.LocationComponent;


public interface Velo extends LocationComponent{

    double getPrice();
    String getDescription();
    boolean isHorsService();
    void incrementUsage();
    String getId();
}
