package org.example.model.velo;
public class VeloClassique extends Velo{

    public VeloClassique(int id, double price, String description){
        super(id, price, description);
    }

    @Override
    public String getDescription(){
        return this.getDescription() + "velo classique";
    }

    @Override
    public double getPrice(){
        return this.getPrice();
    }

}