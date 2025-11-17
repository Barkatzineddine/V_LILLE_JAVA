package org.example.model.decorator;
import org.example.model.velo.Velo;

public class PanierDecorator extends VeloDecorator {
    public PanierDecorator(Velo v) { super(v); }

    @Override
    public double getPrice() { return super.getPrice() + 1.0; }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Panier";
        }
}


