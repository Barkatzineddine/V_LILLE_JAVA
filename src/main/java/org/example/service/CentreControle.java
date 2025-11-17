package org.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.model.Station;
import org.example.model.observer.StationObserver;
import org.example.model.velo.Velo;

public class CentreControle implements StationObserver {

    private static CentreControle instance;
    private Map<Station, Integer> compteurVideMap = new HashMap<>();
    private Map<Station, Integer> compteurPleineMap = new HashMap<>();
    private List<Station> stations;

    private CentreControle() {}

    public static CentreControle getInstance() {
        if (instance == null) {
            instance = new CentreControle();
        }
        return instance;
    }

    @Override
    public void notify(String message, Station station) {
        System.out.println("[CENTRE] Notification reçue : " + message + " — Station " + station.getId());
        // ici tu peux mettre ta logique de redistribution
    }

    public void tick(List<Station> stations) {
    for (Station s : stations) {
        // compteur vide
        if (s.isEmpty()) {
            compteurVideMap.put(s, compteurVideMap.getOrDefault(s, 0) + 1);
        } else {
            compteurVideMap.put(s, 0);
        }

        // compteur pleine
        if (s.isFull()) {
            compteurPleineMap.put(s, compteurPleineMap.getOrDefault(s, 0) + 1);
        } else {
            compteurPleineMap.put(s, 0);
        }

        // alerte et redistribution
        if (compteurVideMap.get(s) >= 2 || compteurPleineMap.get(s) >= 2) {
            redistribuer();
        }
    }
}

    public void enregistrerStations(List<Station> stations) {
        this.stations = stations;
    }

    private void redistribuer() {
            // trouver stations pleines et stations vides
        List<Station> pleines = stations.stream().filter(Station::isFull).toList();
        List<Station> vides = stations.stream().filter(Station::isEmpty).toList();

            // déplacer un vélo de chaque station pleine vers une station vide
        for (int i = 0; i < Math.min(pleines.size(), vides.size()); i++) {
            Station sPleine = pleines.get(i);
            Station sVide = vides.get(i);
            Velo v = sPleine.withdraw();
            if (v != null) {
                sVide.deposit(v);
                System.out.println("[CENTRE] Vélo déplacé de " + sPleine.getId() + " vers " + sVide.getId());
            }
        }
    }
}