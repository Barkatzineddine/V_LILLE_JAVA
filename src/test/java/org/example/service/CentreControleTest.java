package org.example.service;

import org.example.model.Station;
import org.example.model.velo.VeloClassique;
import org.junit.jupiter.api.Test;

public class CentreControleTest {

    @Test
    void centreReceivesNotifications() {
        Station s = new Station("S1", 10);
        CentreControle centre = CentreControle.getInstance();

        s.setObserver(centre);

        // Should not throw
        s.deposit(new VeloClassique("B1", 1.0, "Bike"));
        s.withdraw();
    }
}
