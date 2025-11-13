package org.example.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.example.model.Station;
import org.example.model.Velo;

public class CentreControle {

    private final List<Station> stations;
    private final int maxTempsSansMouvement = 2; // intervalle après lequel on redistribue
    private final List<Integer> tempsSansMouvement; // compteur pour chaque station

    public CentreControle(List<Station> stations) {
        this.stations = stations;
        this.tempsSansMouvement = new ArrayList<>(Collections.nCopies(stations.size(), 0));
    }

    /**
     * Notifie qu'un vélo a été retiré d'une station
     */
    public void notifierVeloRetire(Velo velo, Station station) {
        incrementerTempsSansMouvement();
        int index = stations.indexOf(station);
        if (index >= 0) tempsSansMouvement.set(index, 0);
    }

    /**
     * Notifie qu'un vélo a été déposé dans une station
     */
    public void notifierVeloDepose(Velo velo, Station station) {
        incrementerTempsSansMouvement();
        int index = stations.indexOf(station);
        if (index >= 0) tempsSansMouvement.set(index, 0);
    }

    /**
     * Incrémente le compteur de temps sans mouvement pour toutes les stations
     */
    private void incrementerTempsSansMouvement() {
        for (int i = 0; i < tempsSansMouvement.size(); i++) {
            tempsSansMouvement.set(i, tempsSansMouvement.get(i) + 1);
        }
        redistribuerVelosSiNecessaire();
    }

    /**
     * Redistribue les vélos si une station est restée vide ou pleine trop longtemps
     */
    private void redistribuerVelosSiNecessaire() {
        for (int i = 0; i < stations.size(); i++) {
            Station s = stations.get(i);
            if (tempsSansMouvement.get(i) >= maxTempsSansMouvement) {
                if (s.estVide() || s.estPleine()) {
                    redistribuer(s);
                    tempsSansMouvement.set(i, 0);
                }
            }
        }
    }

    /**
     * Redistribution simple : déplacer un vélo d'une station non vide vers la station cible
     */
    private void redistribuer(Station cible) {
        for (Station s : stations) {
            if (!s.equals(cible) && !s.estVide() && !cible.estPleine()) {
                Velo velo = s.retirerVelo();
                if (velo != null) {
                    cible.ajouterVelo(velo);
                    System.out.println("Redistribution : Velo " + velo.getId() +
                            " de Station " + s.getId() + " vers Station " + cible.getId());
                    break; // déplacer un vélo à la fois
                }
            }
        }
    }

    /**
     * Retourne la liste des stations supervisées
     */
    public List<Station> getStations() {
        return stations;
    }
}
