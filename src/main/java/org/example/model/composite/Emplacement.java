package org.example.model.composite;

import org.example.model.velo.Velo;

public class Emplacement implements LocationComponent {

    private Velo velo;

    public Emplacement(Velo velo) {
        this.velo = velo;
    }

    public void setVelo(Velo v) { this.velo = v; }
    public Velo getVelo() { return velo; }

    @Override
    public void displayStatus() {
        if (velo == null) {
            System.out.println("    * Emplacement : vide");
        } else {
            velo.displayStatus();
        }
    }
}
