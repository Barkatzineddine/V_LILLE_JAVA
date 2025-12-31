package org.example.model.decorator;

import org.example.model.bike.Bike;
import org.example.model.bike.ClassicBike;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecoratorTest {

    @Test
    void basketDecoratorShouldIncreasePriceAndDescription() {
        Bike b = new ClassicBike("C1", 1.0, "Classic Bike");
        Bike withBasket = new BasketDecorator(b);

        assertEquals(2.0, withBasket.getPrice(), 1e-9);
        assertTrue(withBasket.getDescription().contains("Basket"));
        assertEquals("C1", withBasket.getId());
    }

    @Test
    void luggageRackDecoratorShouldIncreasePriceAndDescription() {
        Bike b = new ClassicBike("C1", 1.0, "Classic Bike");
        Bike withRack = new LuggageRackDecorator(b);

        assertEquals(1.5, withRack.getPrice(), 1e-9);
        assertTrue(withRack.getDescription().contains("Luggage Rack"));
    }

    @Test
    void decoratorsShouldStack() {
        Bike b = new ClassicBike("C1", 1.0, "Classic Bike");
        Bike full = new BasketDecorator(new LuggageRackDecorator(b));

        assertEquals(2.5, full.getPrice(), 1e-9);
        assertTrue(full.getDescription().contains("Basket"));
        assertTrue(full.getDescription().contains("Luggage Rack"));
    }
}
