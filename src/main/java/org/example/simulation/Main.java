package org.example.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.example.model.Station;
import org.example.model.Utilisateur;
import org.example.model.composite.Emplacement;
import org.example.model.composite.LocationComponent;
import org.example.model.composite.NetworkComposite;
import org.example.model.composite.StationComposite;
import org.example.model.decorator.PanierDecorator;
import org.example.model.decorator.PorteBagagesDecorator;
import org.example.model.velo.Velo;
import org.example.model.velo.VeloClassique;
import org.example.model.velo.VeloElectrique;
import org.example.service.CentreControle;
import org.example.service.strategy.SimpleRedistributionStrategy;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("========== SIMULATION VÉLOS EN LIBRE-SERVICE ==========\n");

        // --- 1) Création des stations ---
        Station s1 = new Station("S1", 10);
        Station s2 = new Station("S2", 12);
        Station s3 = new Station("S3", 15);

        List<Station> stations = List.of(s1, s2, s3);

        // --- 2) Centre de contrôle (Singleton + Observer + Strategy) ---
        CentreControle centre = CentreControle.getInstance();
        centre.enregistrerStations(stations);
        centre.setStrategy(new SimpleRedistributionStrategy());

        s1.setObserver(centre);
        s2.setObserver(centre);
        s3.setObserver(centre);

        // --- 3) Création de vélos (avec Decorator) ---
        depositInitialBikes(s1, s2, s3);

        // --- 4) Création des utilisateurs ---
        Utilisateur u1 = new Utilisateur("Alice", 12);
        Utilisateur u2 = new Utilisateur("Bob", 4);
        List<Utilisateur> utilisateurs = List.of(u1, u2);

        // --- 5) Boucle de simulation ---
        Random r = new Random();

        for (int t = 0; t < 10; t++) {
            System.out.println("\n========== TOUR " + t + " ==========");

            // Tick des stations
            stations.forEach(Station::tick);

            // Actions des utilisateurs
            for (Utilisateur u : utilisateurs) simulateUserAction(u, stations, r);

            // Redistribution automatique
            centre.tick(stations);

            // AFFICHAGE RÉSEAU (Composite)
            System.out.println("\n--- ÉTAT ACTUEL DU RÉSEAU ---");
            displayNetwork(stations);

            Thread.sleep(800); // joli effet visuel
        }

        System.out.println("\n========== FIN DE LA SIMULATION ==========");
    }

    // ---------------------------------------------------------------
    // MÉTHODES UTILITAIRES
    // ---------------------------------------------------------------

    private static void depositInitialBikes(Station s1, Station s2, Station s3) {
        s1.deposit(new VeloClassique("C1", 1.0, "Vélo Classique"));
        s1.deposit(new PanierDecorator(new VeloClassique("C2", 1.0, "Classique + Panier")));

        s2.deposit(new VeloElectrique("E1", 2.0, "Électrique"));
        s2.deposit(new PorteBagagesDecorator(new VeloClassique("C3", 1.0, "Classique + Porte-Bagages")));

        s3.deposit(new PanierDecorator(
                        new PorteBagagesDecorator(
                            new VeloElectrique("E2", 2.0, "Électrique Full Options"))));
    }

    private static void simulateUserAction(Utilisateur u, List<Station> stations, Random r) {
        Station s = stations.get(r.nextInt(stations.size()));

        if (r.nextBoolean()) {
            boolean ok = u.louerVelo(s);
            System.out.println("• " + u.getNom() + " essaie de louer à " + s.getId()
                             + " → " + (ok ? "SUCCÈS" : "ÉCHEC"));
        } else {
            System.out.println("• " + u.getNom() + " rend son vélo à " + s.getId());
            u.rendreVelo(s);
        }
    }

    // --- Conversion du modèle vers le Composite pour affichage ---
    private static void displayNetwork(List<Station> stations) {

        List<LocationComponent> stationComposites = new ArrayList<>();

        for (Station st : stations) {
            List<LocationComponent> emps = new ArrayList<>();

            // Génère des Emplacements à partir de la station réelle
            for (int i = 0; i < st.getCapacity(); i++) {
                Velo v = getVeloAtSlot(st, i); // méthode utilitaire
                emps.add(new Emplacement(v));
            }

            stationComposites.add(new StationComposite(st.getId(), emps));
        }

        NetworkComposite reseau = new NetworkComposite(stationComposites);

        reseau.displayStatus();
    }

    // Récupère le vélo dans l'emplacement i d'une station
    private static Velo getVeloAtSlot(Station s, int index) {
        try {
            // ta classe Station ne donne pas accès direct aux emplacements,
            // donc je reconstruis via withdraw / deposit simulé
            Velo v = typeofAccessHack(s, index);
            return v;
        } catch (Exception e) {
            return null;
        }
    }

    // ⚠️ Hack technique pour accéder indirectement à un emplacement
    // (car tes emplacements sont privés)
    private static Velo typeofAccessHack(Station s, int index) {
        // méthode non intrusive :
        // on redépose temporairement les vélos retirés
        List<Velo> temp = new ArrayList<>();

        Velo target = null;

        for (int i = 0; i <= index; i++) {
            Velo v = s.withdraw();
            if (i == index) target = v;
            temp.add(v);
        }

        // re-déposer les vélos en ordre
        for (Velo v : temp) {
            if (v != null) s.deposit(v);
        }

        return target;
    }
}
