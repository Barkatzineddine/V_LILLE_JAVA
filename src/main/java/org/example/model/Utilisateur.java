package org.example.model;
import org.example.model.velo.Velo;

public class Utilisateur {
    private String nom;
    private double solde;
    private Velo veloActuel;

    public Utilisateur(String nom, double solde) {
        this.nom = nom;
        this.solde = solde;
    }

    public boolean louerVelo(Station station) {
        Velo v = station.withdraw();
        if (v == null) return false;

        double prix = v.getPrice();

        if (solde < prix) {
            station.deposit(v); // remettre vélo
            return false;
        }

        solde -= prix;
        veloActuel = v;
        return true;
    }

    public void rendreVelo(Station station) {
        if (veloActuel != null && station.deposit(veloActuel)) {
            veloActuel = null;
        }
    }
}
