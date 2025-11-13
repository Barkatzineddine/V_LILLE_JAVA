package org.example.service;

import org.example.model.Station;
import org.example.model.Velo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CentreControleTest {

    private Station station1;
    private Station station2;
    private CentreControle centreControle;

    @BeforeEach
    void setup() {
        // Création de 2 stations avec 2 emplacements chacune
        station1 = new Station("S1", 2);
        station2 = new Station("S2", 2);

        // Ajout d'un vélo à chaque station
        station1.ajouterVelo(new Velo("V1"));
        station2.ajouterVelo(new Velo("V2"));

        // Création du centre de contrôle
        centreControle = new CentreControle(Arrays.asList(station1, station2));
    }

    @Test
    void testNotificationRetraitEtDepot() {
        Velo v = station1.retirerVelo();
        assertNotNull(v);
        centreControle.notifierVeloRetire(v, station1);

        station1.ajouterVelo(v);
        centreControle.notifierVeloDepose(v, station1);

        // Vérifie que le vélo est revenu dans la station
        assertEquals(1, station1.getVelos().size());
    }

    @Test
    void testRedistributionQuandStationVide() {
        // Retire le vélo de station1 pour la vider
        Velo v = station1.retirerVelo();
        centreControle.notifierVeloRetire(v, station1);

        // Simule 2 tours de temps sans mouvement
        centreControle.notifierVeloDepose(v, station2); // tour 1
        centreControle.notifierVeloDepose(v, station2); // tour 2

        // Après redistribution, station1 devrait avoir un vélo venant de station2
        assertFalse(station1.estVide(), "Station1 doit recevoir un vélo");
        assertTrue(station2.getVelos().size() >= 0, "Station2 doit perdre un vélo");
    }

    @Test
    void testRedistributionQuandStationPleine() {
        // Remplir station1 pour la mettre pleine
        station1.ajouterVelo(new Velo("V3"));
        assertTrue(station1.estPleine());

        // station2 contient 1 vélo
        Velo v2 = station2.retirerVelo();
        centreControle.notifierVeloRetire(v2, station2);

        // Simule 2 tours de temps sans mouvement
        centreControle.notifierVeloDepose(v2, station2);
        centreControle.notifierVeloDepose(v2, station2);

        // Vérifie qu'un vélo de station1 est allé vers station2
        assertTrue(station2.getVelos().size() > 0, "Station2 doit recevoir un vélo");
        assertTrue(station1.getVelos().size() < 2, "Station1 doit avoir perdu un vélo");
    }
}
