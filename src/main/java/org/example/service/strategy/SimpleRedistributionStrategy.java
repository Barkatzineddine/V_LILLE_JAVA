package org.example.service.strategy;

import java.util.List;

import org.example.model.Station;
import org.example.model.bike.Bike;

/**
 * Simple strategy: move one bike from each full station to each empty station (pairwise).
 */
public class SimpleRedistributionStrategy implements RedistributionStrategy {

    @Override
    public void redistribute(List<Station> stations) {
        List<Station> full = stations.stream().filter(Station::isFull).toList();
        List<Station> empty = stations.stream().filter(Station::isEmpty).toList();

        int k = Math.min(full.size(), empty.size());
        for (int i = 0; i < k; i++) {
            Station from = full.get(i);
            Station to = empty.get(i);

            Bike b = from.withdraw();
            if (b != null) {
                to.deposit(b);
                System.out.println("[STRATEGY] Simple redistribution: " + from.getId() + " -> " + to.getId());
            }
        }
    }
}
