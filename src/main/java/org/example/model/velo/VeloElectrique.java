package org.example.model.velo;

public class VeloElectrique extends Velo{
    private double diffPrice = 75.99;
    private double batteryLevel = 100;

    public VeloElectrique(int id, double price, String description){
        super(id, price, description);
    }

    @Override
    public String getDescription(){
        return this.getDescription() + "velo electrique";
    }

    @Override
    public double getPrice(){
        return this.getPrice()+this.diffPrice;
    }
    
    public double getBatteryLevel(){
        return this.batteryLevel;
    }

    public void charge(int time){
        int i;
        for(i=0; i<=time;i++){
            if(batteryLevel==100)
                break;
            batteryLevel=+1;
        }
    }

}