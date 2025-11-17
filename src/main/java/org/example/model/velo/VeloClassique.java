package org.example.model.velo;

public class VeloClassique implements Velo{

    private final String id;
    private double price;
    private String description;
    private boolean horsService = false;
    private int uses = 0;

    public VeloClassique(String id, double price, String description){
        this.id = id;
        this.price = price;
        this.description = description;
    }

    @Override
    public void incrementUsage() { 
        uses++;
        if (uses > 10) horsService = true;
    }

    @Override
    public String getDescription(){
        return this.description;
    }

    @Override
    public double getPrice(){
        return this.price;
    }

    @Override
    public boolean isHorsService() { return horsService; }

    @Override
    public String getId() { return id; }

}