package org.example.model;

import org.example.model.bike.Bike;
import org.example.model.bike.ClassicBike;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StationTest {

    @Test
    void depositShouldIncreaseOccupiedAndDecreaseFree() {
        Station s = new Station("S1", 2);
        assertEquals(0, s.occupiedSlots());
        assertEquals(2, s.freeSlots());

        assertTrue(s.deposit(new ClassicBike("C1", 1.0, "Classic")));
        assertEquals(1, s.occupiedSlots());
        assertEquals(1, s.freeSlots());
    }

    @Test
    void depositShouldFailWhenFull() {
        Station s = new Station("S1", 1);
        assertTrue(s.deposit(new ClassicBike("C1", 1.0, "Classic")));
        assertFalse(s.deposit(new ClassicBike("C2", 1.0, "Classic")));
        assertEquals(1, s.occupiedSlots());
        assertEquals(0, s.freeSlots());
        assertTrue(s.isFull());
    }

    @Test
    void withdrawShouldReturnNullWhenEmpty() {
        Station s = new Station("S1", 2);
        assertNull(s.withdraw());
        assertTrue(s.isEmpty());
    }

    @Test
    void withdrawShouldRemoveFirstBikeFIFO() {
        Station s = new Station("S1", 3);
        Bike b1 = new ClassicBike("C1", 1.0, "Classic");
        Bike b2 = new ClassicBike("C2", 1.0, "Classic");

        assertTrue(s.deposit(b1));
        assertTrue(s.deposit(b2));

        Bike w1 = s.withdraw();
        Bike w2 = s.withdraw();

        assertNotNull(w1);
        assertNotNull(w2);
        assertEquals("C1", w1.getId());
        assertEquals("C2", w2.getId());
        assertEquals(0, s.occupiedSlots());
    }

    @Test
    void tickShouldUpdateEmptyAndFullStreaks() {
        Station s = new Station("S1", 1);

        s.tick(); // empty
        s.tick(); // empty
        assertEquals(2, s.getEmptyStreak());
        assertEquals(0, s.getFullStreak());

        assertTrue(s.deposit(new ClassicBike("C1", 1.0, "Classic"))); // full
        s.tick();
        s.tick();
        assertEquals(0, s.getEmptyStreak());
        assertEquals(2, s.getFullStreak());
    }
}
