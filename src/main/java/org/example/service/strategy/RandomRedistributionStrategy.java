package org.example.service.strategy;

import java.util.List;
import java.util.Random;

import org.example.model.Station;
import org.example.model.bike.Bike;

/** Random strategy: for each full station, move one bike to a random empty station (if any). */
public class RandomRedistributionStrategy implements RedistributionStrategy {

    private final Random rng = new Random();

    @Override
    public void redistribute(List<Station> stations) {
        List<Station> full = stations.stream().filter(Station::isFull).toList();
        List<Station> empty = stations.stream().filter(Station::isEmpty).toList();

        if (empty.isEmpty()) return;

        for (Station from : full) {
            Station to = empty.get(rng.nextInt(empty.size()));
            Bike b = from.withdraw();
            if (b != null) {
                to.deposit(b);
                System.out.println("[STRATEGY] Random redistribution: " + from.getId() + " -> " + to.getId());
            }
        }
    }
}
