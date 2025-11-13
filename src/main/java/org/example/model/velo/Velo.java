package org.example.model.velo;


public abstract class Velo {
    private final int id;
    private double price;
    private String description;
    

    public Velo(int id, double price, String description) {
        this.id = id;
        this.price = price;
        this.description = description;
    }

    

    // getters et setters
    public int getId() { return id; }
    public abstract String getDescription();
    public abstract double getPrice();
}
