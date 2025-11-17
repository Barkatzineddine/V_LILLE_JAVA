package org.example.model.observer;

import org.example.model.Station;

public interface StationObserver {
    
    void notify(String message, Station station);
}
