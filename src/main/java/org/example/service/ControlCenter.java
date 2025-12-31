package org.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.model.Station;
import org.example.model.observer.StationObserver;
import org.example.service.strategy.RedistributionStrategy;


public class ControlCenter implements StationObserver {

    private static ControlCenter instance;

    private final Map<Station, Integer> emptyStreak = new HashMap<>();
    private final Map<Station, Integer> fullStreak = new HashMap<>();

    private RedistributionStrategy strategy;

    private ControlCenter() {}

    public static ControlCenter getInstance() {
        if (instance == null) instance = new ControlCenter();
        return instance;
    }

    public void registerStations(List<Station> stations) {
        for (Station s : stations) {
            emptyStreak.put(s, 0);
            fullStreak.put(s, 0);
        }
    }

    public void setStrategy(RedistributionStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void notify(String message, Station station) {
        System.out.println("[CENTER] " + message + " — Station " + station.getId());
    }

    /** Called once per simulation step. */
    public void tick(List<Station> stations) {
        for (Station s : stations) {
            emptyStreak.put(s, s.isEmpty() ? emptyStreak.getOrDefault(s, 0) + 1 : 0);
            fullStreak.put(s, s.isFull() ? fullStreak.getOrDefault(s, 0) + 1 : 0);
        }

        boolean trigger = stations.stream().anyMatch(s ->
                emptyStreak.getOrDefault(s, 0) >= 2 || fullStreak.getOrDefault(s, 0) >= 2);

        if (trigger && strategy != null) {
            System.out.println("[CENTER] Redistribution triggered");
            strategy.redistribute(stations);

            for (Station s : stations) {
                emptyStreak.put(s, 0);
                fullStreak.put(s, 0);
            }
        }
    }
}
