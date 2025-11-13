package org.example.model;
import java.util.ArrayList;
import java.util.List;

import org.example.model.velo.Velo;

public class Station {
    private final int id;
    private final int capacite;
    private List<Velo> velos;

    public Station(int id, int capacite) {
        this.id = id;
        this.capacite = capacite;
        this.velos = new ArrayList<>();
    }

    public boolean ajouterVelo(Velo velo) {
        if (velos.size() < capacite) {
            velos.add(velo);
            return true;
        }
        return false;
    }

    public Velo retirerVelo() {
        if (!velos.isEmpty()) {
            return velos.remove(0);
        }
        return null;
    }

    public boolean estPleine() { return velos.size() >= capacite; }
    public boolean estVide() { return velos.isEmpty(); }
    public List<Velo> getVelos() { return velos; }
    public int getId() { return id; }
}
