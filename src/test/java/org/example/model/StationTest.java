package org.example.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class StationTest {

    @Test
    public void testAjoutRetraitVelo() {
        Station s = new Station(1, 2);
        Velo v1 = new Velo(1, "classique", false, false);
        Velo v2 = new Velo(2, "electrique", true, false);

        assertTrue(s.ajouterVelo(v1));
        assertTrue(s.ajouterVelo(v2));
        assertFalse(s.ajouterVelo(new Velo(3, "classique", false, false)));

        assertEquals(v1, s.retirerVelo());
        assertEquals(v2, s.retirerVelo());
        assertNull(s.retirerVelo());
    }
}
