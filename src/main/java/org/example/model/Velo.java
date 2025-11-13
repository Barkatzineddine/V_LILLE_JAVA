package org.example.model;


public class Velo {
    private final int id;
    private final String type; // "classique", "electrique", "pliant", etc.
    private boolean aPanier;
    private boolean aPorteBagages;
    private boolean estEnPanne;
    private boolean estVole;

    public Velo(int id, String type, boolean aPanier, boolean aPorteBagages) {
        this.id = id;
        this.type = type;
        this.aPanier = aPanier;
        this.aPorteBagages = aPorteBagages;
        this.estEnPanne = false;
        this.estVole = false;
    }

    public void marquerEnPanne() { this.estEnPanne = true; }
    public void marquerVole() { this.estVole = true; }
    public boolean estDisponible() { return !estEnPanne && !estVole; }

    // getters et setters
    public int getId() { return id; }
    public String getType() { return type; }
    public boolean aPanier() { return aPanier; }
    public boolean aPorteBagages() { return aPorteBagages; }
    public boolean estEnPanne() { return estEnPanne; }
    public boolean estVole() { return estVole; }
}
