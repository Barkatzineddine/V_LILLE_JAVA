package org.example.model.velo;

public class VeloElectrique implements Velo{

    private double batteryLevel = 100;
    private final String id;
    private double price;
    private String description;
    private boolean horsService = false;
    private int uses = 0;

    public VeloElectrique(String id, double price, String description){
        this.id = id;
        this.price = price;
        this.description = description;
    }

    @Override
    public boolean isHorsService() { return horsService; }

    @Override
    public String getId() { return id; }

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
    
    public double getBatteryLevel(){
        return this.batteryLevel;
    }

    public void charge(int time){
        int i;
        for(i=0; i<=time;i++){
            if(batteryLevel == 100)
                break;
            batteryLevel=+1;
        }
    }

    @Override
    public void displayStatus() {
        System.out.println("  - Vélo " + id + " : " + getDescription());
    }

}