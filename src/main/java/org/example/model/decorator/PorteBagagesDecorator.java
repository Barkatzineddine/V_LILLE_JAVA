package org.example.model.decorator;
import org.example.model.velo.Velo;

public class PorteBagagesDecorator extends VeloDecorator {
    public PorteBagagesDecorator(Velo v) { super(v); }

    @Override
    public double getPrice() { return super.getPrice() + 0.5; }

    @Override
    public String getDescription() {
         return super.getDescription() + " + Porte-Bagages"; 
        }
}
