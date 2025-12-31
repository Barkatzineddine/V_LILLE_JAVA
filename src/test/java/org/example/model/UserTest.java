package org.example.model;

import org.example.model.bike.ClassicBike;
import org.example.model.decorator.BasketDecorator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void rentBikeShouldFailIfStationEmpty() {
        Station s = new Station("S1", 2);
        User u = new User("Alice", 10);

        assertFalse(u.rentBike(s));
    }

    @Test
    void rentBikeShouldFailIfUserAlreadyHasBike() {
        Station s = new Station("S1", 2);
        s.deposit(new ClassicBike("C1", 1.0, "Classic"));
        s.deposit(new ClassicBike("C2", 1.0, "Classic"));

        User u = new User("Alice", 10);
        assertTrue(u.rentBike(s));
        assertFalse(u.rentBike(s)); // second rent forbidden
        assertEquals(1, s.occupiedSlots()); // only one bike removed
    }

    @Test
    void rentBikeShouldFailIfBalanceInsufficientAndBikeReturnedToStation() {
        Station s = new Station("S1", 2);
        // basket adds extra cost
        s.deposit(new BasketDecorator(new ClassicBike("C1", 1.0, "Classic")));

        User u = new User("Bob", 0.5);
        assertFalse(u.rentBike(s));
        // bike should have been deposited back
        assertEquals(1, s.occupiedSlots());
    }

    @Test
    void successfulRentShouldDecreaseBalance() {
        Station s = new Station("S1", 2);
        s.deposit(new ClassicBike("C1", 2.0, "Classic"));

        User u = new User("Alice", 10);
        assertTrue(u.rentBike(s));
        assertEquals(8.0, u.getBalance(), 1e-9);
        assertEquals(0, s.occupiedSlots());
    }

    @Test
    void returnBikeShouldDepositBackToStation() {
        Station s1 = new Station("S1", 2);
        Station s2 = new Station("S2", 2);

        s1.deposit(new ClassicBike("C1", 1.0, "Classic"));

        User u = new User("Alice", 10);
        assertTrue(u.rentBike(s1));
        assertEquals(0, s1.occupiedSlots());

        assertTrue(u.returnBike(s2));
        assertEquals(1, s2.occupiedSlots());

        // returning again should fail (no bike)
        assertFalse(u.returnBike(s2));
    }

    @Test
    void returnBikeShouldFailIfStationFull() {
        Station s1 = new Station("S1", 1);
        Station s2 = new Station("S2", 1);

        s1.deposit(new ClassicBike("C1", 1.0, "Classic"));
        s2.deposit(new ClassicBike("C2", 1.0, "Classic")); // s2 full

        User u = new User("Alice", 10);
        assertTrue(u.rentBike(s1));
        assertFalse(u.returnBike(s2)); // cannot deposit
    }
}
