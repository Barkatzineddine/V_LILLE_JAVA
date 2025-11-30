package org.example.model;

import org.example.model.velo.VeloClassique;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class StationTest {

    @Test
    void depositAndWithdraw() {
        Station s = new Station("S1", 10);
        VeloClassique v = new VeloClassique("C1", 1.0, "Classique");

        assertTrue(s.deposit(v));
        assertFalse(s.isEmpty());
        
        assertEquals(v, s.withdraw());
        assertTrue(s.isEmpty());
    }
}
