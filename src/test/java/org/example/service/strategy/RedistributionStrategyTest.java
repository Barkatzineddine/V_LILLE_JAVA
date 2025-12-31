package org.example.service.strategy;

import org.example.model.Station;
import org.example.model.bike.ClassicBike;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RedistributionStrategyTest {

    @Test
    void simpleStrategyShouldMoveBikeFromFullToEmpty() {
        Station full = new Station("FULL", 1);
        Station empty = new Station("EMPTY", 2);

        assertTrue(full.deposit(new ClassicBike("C1", 1.0, "Classic")));
        assertTrue(full.isFull());
        assertTrue(empty.isEmpty());

        RedistributionStrategy strategy = new SimpleRedistributionStrategy();
        strategy.redistribute(List.of(full, empty));

        assertEquals(0, full.occupiedSlots());
        assertEquals(1, empty.occupiedSlots());
    }

    @Test
    void randomStrategyShouldNotCrashAndShouldMoveIfPossible() {
        Station full = new Station("FULL", 1);
        Station empty = new Station("EMPTY", 2);

        full.deposit(new ClassicBike("C1", 1.0, "Classic"));

        RedistributionStrategy strategy = new RandomRedistributionStrategy();
        strategy.redistribute(List.of(full, empty));

        // With one full and one empty, it should move 1 bike.
        assertEquals(0, full.occupiedSlots());
        assertEquals(1, empty.occupiedSlots());
    }
}
