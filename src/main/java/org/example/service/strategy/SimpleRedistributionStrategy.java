package org.example.service.strategy;

import java.util.List;

import org.example.model.Station;
import org.example.model.velo.Velo;

public class SimpleRedistributionStrategy implements RedistributionStrategy {

    @Override
    public void redistribute(List<Station> stations) {
        List<Station> pleines = stations.stream().filter(Station::isFull).toList();
        List<Station> vides = stations.stream().filter(Station::isEmpty).toList();

        for (int i = 0; i < Math.min(pleines.size(), vides.size()); i++) {
            Station full = pleines.get(i);
            Station empty = vides.get(i);

            Velo v = full.withdraw();
            if (v != null) {
                empty.deposit(v);
                System.out.println("[STRATEGY] Simple redistribution: "
                        + full.getId() + " -> " + empty.getId());
            }
        }
    }
}
