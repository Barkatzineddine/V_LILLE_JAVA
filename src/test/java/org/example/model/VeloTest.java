package org.example.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class VeloTest {

    @Test
    public void testDisponibilite() {
        Velo v = new Velo(1, "classique", true, false);
        assertTrue(v.estDisponible());

        v.marquerEnPanne();
        assertFalse(v.estDisponible());

        Velo v2 = new Velo(2, "electrique", false, true);
        v2.marquerVole();
        assertFalse(v2.estDisponible());
    }
}
