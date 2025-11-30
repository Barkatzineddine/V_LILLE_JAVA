package org.example.model;

import org.example.model.velo.VeloClassique;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UtilisateurTest {

    @Test
    void louerEtRendreVelo() {
        Station s = new Station("S1", 10);
        Utilisateur u = new Utilisateur("Alice", 10);

        VeloClassique v = new VeloClassique("C1", 1.0, "Classique");
        s.deposit(v);

        assertTrue(u.louerVelo(s));
        assertEquals(9, u.getSolde()); // 10 - 1

        s.deposit(new VeloClassique("C2", 2.0, "Classique2")); // to ensure slot available
        u.rendreVelo(s);

        assertTrue(s.occupiedSlots() > 0);
    }
}
