package org.example.service.strategy;

import java.util.List;

import org.example.model.Station;

/**
 * Strategy interface defining a bike redistribution policy
 * between stations.
*/

public interface RedistributionStrategy {
    void redistribute(List<Station> stations);
}