package org.example.service.strategy;

import java.util.List;

import org.example.model.Station;

public interface RedistributionStrategy {
    void redistribute(List<Station> stations);
}