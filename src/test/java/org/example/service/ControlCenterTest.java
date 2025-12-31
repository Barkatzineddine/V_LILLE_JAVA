package org.example.service;

import org.example.model.Station;
import org.example.model.bike.ClassicBike;
import org.example.service.strategy.RedistributionStrategy;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class ControlCenterTest {

    @Test
    void controlCenterShouldTriggerRedistributionWhenEmptyOrFullStreakReaches2() {
        Station s1 = new Station("S1", 1); // will be empty
        Station s2 = new Station("S2", 1); // will be full
        s2.deposit(new ClassicBike("C1", 1.0, "Classic"));

        ControlCenter center = ControlCenter.getInstance();
        center.registerStations(List.of(s1, s2));

        AtomicInteger calls = new AtomicInteger(0);
        RedistributionStrategy spy = stations -> calls.incrementAndGet();
        center.setStrategy(spy);

        // simulate 2 ticks where s1 is empty and s2 is full
        center.tick(List.of(s1, s2));
        center.tick(List.of(s1, s2));

        assertEquals(1, calls.get(), "Redistribution should be triggered once");
    }
}
