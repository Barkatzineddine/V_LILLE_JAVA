package org.example.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class UtilisateurTest {

    @Test
    public void testPaiement() {
        Utilisateur u = new Utilisateur(1, "Alice", 10.0);
        assertTrue(u.payer(5.0));
        assertEquals(5.0, u.getSolde());
        assertFalse(u.payer(10.0));
        assertEquals(5.0, u.getSolde());
    }
}
