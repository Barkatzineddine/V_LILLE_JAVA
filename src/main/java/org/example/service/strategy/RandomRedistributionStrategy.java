package org.example.service.strategy;

import java.util.Collections;
import java.util.List;

import org.example.model.Station;
import org.example.model.velo.Velo;

public class RandomRedistributionStrategy implements RedistributionStrategy {

    @Override
    public void redistribute(List<Station> stations) {
        Collections.shuffle(stations);

        for (Station s : stations) {
            if (s.isFull()) {
                for (Station other : stations) {
                    if (other.isEmpty()) {
                        Velo v = s.withdraw();
                        if (v != null) {
                            other.deposit(v);
                            System.out.println("[STRATEGY] Random redistribution: "
                                    + s.getId() + " -> " + other.getId());
                        }
                        break;
                    }
                }
            }
        }
    }
}
