package org.example.model;

import org.example.model.decorator.PanierDecorator;
import org.example.model.decorator.PorteBagagesDecorator;
import org.example.model.velo.Velo;
import org.example.model.velo.VeloClassique;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class VeloDecoratorTest {

    @Test
    void priceShouldStack() {
        Velo v = new VeloClassique("C1", 1.0, "Classique");
        v = new PanierDecorator(v);  // +1.0
        v = new PorteBagagesDecorator(v); // +0.5

        assertEquals(2.5, v.getPrice());
    }
}
