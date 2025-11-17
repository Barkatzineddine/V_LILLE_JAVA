package org.example.model.decorator;
import org.example.model.velo.Velo;

public abstract class VeloDecorator implements Velo {
    protected Velo decoratedVelo;

    public VeloDecorator(Velo v) { 
        this.decoratedVelo = v; 
    }

    @Override
    public double getPrice() { return decoratedVelo.getPrice(); }

    @Override
    public String getDescription() { return decoratedVelo.getDescription(); }

    @Override
    public boolean isHorsService() { return decoratedVelo.isHorsService(); }

    @Override
    public void incrementUsage() { decoratedVelo.incrementUsage(); }

    @Override
    public String getId() { return decoratedVelo.getId(); };
}
