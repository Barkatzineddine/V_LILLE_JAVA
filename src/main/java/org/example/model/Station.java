package org.example.model;
import java.util.ArrayList;
import java.util.List;

import org.example.model.observer.StationObserver;
import org.example.model.velo.Velo;

public class Station {

    private final String id;
    private final int capacity;                
    private final List<Velo> emplacements; 
    private int compteurVide = 0;
    private int compteurPleine = 0;
    private StationObserver observer;

    public Station(String id, int capacity) {
        if (capacity < 10 || capacity > 20) {
            throw new IllegalArgumentException("Capacity must be between 10 and 20");
        }
        this.id = id;
        this.capacity = capacity;
        this.emplacements = new ArrayList<>(capacity);

        for (int i = 0; i < capacity; i++) {
            emplacements.add(null);
        }
    }

    public void setObserver(StationObserver observer) {
    this.observer = observer;
    }

    public void tick() {
    if (isEmpty()) {
        this.compteurVide++;
    } else {
       this.compteurVide = 0;
    }

    if (isFull()) {
        this.compteurPleine++;
    } else {
        this.compteurPleine = 0;
    }
    }

    public String getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public int occupiedSlots() {
        int count = 0;
        for (Velo v : emplacements) {
            if (v != null) count++;
        }
        return count;
    }

    public int freeSlots() {
        return capacity - occupiedSlots();
    }

    public boolean deposit(Velo v) {
        for (int i = 0; i < capacity; i++) {
            if (emplacements.get(i) == null) {
                emplacements.set(i, v);
                if (observer != null) observer.notify("Dépôt de vélo", this);
                return true;
            }
        }
        if (observer != null) observer.notify("Station pleine, dépôt impossible", this);
        return false;
    }

    public Velo withdraw() {
        for (int i = 0; i < capacity; i++) {
            Velo v = emplacements.get(i);
            if (v != null && !v.isHorsService()) {
                emplacements.set(i, null);
                v.incrementUsage();
                if (observer != null) observer.notify("Retrait de vélo", this);
                return v;
            }
        }
        if (observer != null) observer.notify("Station vide, retrait impossible", this);
        return null;
    }

    public boolean isEmpty() {
        return occupiedSlots() == 0;
    }

    /** Pour vérifier si station est pleine */
    public boolean isFull() {
        return freeSlots() == 0;
    }

   
}
