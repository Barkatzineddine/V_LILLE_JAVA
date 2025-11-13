package org.example.model;

public class Utilisateur {
    private final int id;
    private final String nom;
    private double solde;

    public Utilisateur(int id, String nom, double solde) {
        this.id = id;
        this.nom = nom;
        this.solde = solde;
    }

    public boolean payer(double montant) {
        if (solde >= montant) {
            solde -= montant;
            return true;
        }
        return false;
    }

    public double getSolde() { return solde; }
    public String getNom() { return nom; }
    public int getId() { return id; }
}
