package org.example.service;

import java.util.List;

import org.example.model.Station;
import org.example.model.velo.VeloClassique;
import org.example.service.strategy.SimpleRedistributionStrategy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class StrategyTest {

    @Test
    void simpleRedistributionMovesBike() {
    Station full = new Station("F", 10);
    Station empty = new Station("E", 10);

    // Fill the full station
    for (int i = 0; i < 10; i++) {
        full.deposit(new VeloClassique("B" + i, 1.0, "Bike"));
    }

    assertTrue(full.isFull());
    assertTrue(empty.isEmpty());

    SimpleRedistributionStrategy strategy = new SimpleRedistributionStrategy();
    strategy.redistribute(List.of(full, empty));

    assertEquals(1, empty.occupiedSlots());
    assertEquals(9, full.occupiedSlots());  // one moved
}

}
