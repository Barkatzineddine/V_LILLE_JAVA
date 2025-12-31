package org.example.model.composite;

import org.example.model.bike.ClassicBike;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompositeDisplayTest {

    @Test
    void networkCompositeShouldPrintSomething() {
        LocationComponent station = new StationComposite(
                "S1",
                List.of(new Slot(new ClassicBike("C1", 1.0, "Classic Bike")),
                        new Slot(null))
        );

        NetworkComposite network = new NetworkComposite(List.of(station));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(out));

        try {
            network.displayStatus();
        } finally {
            System.setOut(old);
        }

        String printed = out.toString();
        assertTrue(printed.contains("Station"), "Should print station header");
        assertTrue(printed.length() > 10, "Should print non-empty output");
    }
}
