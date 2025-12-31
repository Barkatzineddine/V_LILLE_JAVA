package org.example.model.bike;

public class ClassicBike implements Bike {

    private final String id;
    private final double basePrice;
    private final String description;

    private int usageCount = 0;
    private boolean outOfService = false;

    public ClassicBike(String id, double basePrice, String description) {
        this.id = id;
        this.basePrice = basePrice;
        this.description = description;
    }

    @Override
    public double getPrice() {
        return basePrice;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isOutOfService() {
        return outOfService;
    }

    @Override
    public void incrementUsage() {
        usageCount++;
        // Example rule: after 5 rentals, the bike becomes out of service.
        if (usageCount >= 5) {
            outOfService = true;
        }
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void displayStatus() {
        System.out.println("    * Bike " + id + " : " + getDescription() +
                " (price=" + getPrice() + ", outOfService=" + outOfService + ")");
    }
}
