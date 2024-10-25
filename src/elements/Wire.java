package elements;

import observer.Subject;

import java.util.ArrayList;
import java.util.List;

import observer.Observer;

public class Wire extends Element implements Observer, Subject {
    private boolean powered;
    private List<Observer> observers;

    public Wire(int x, int y, int z) {
        super(x, y, z);
        this.powered = false;
        this.observers = new ArrayList<>();
        this.addStateChange(powered, 0);
    }

    @Override
    public void update(int tick) {
        System.out.println("Wire at (" + x + ", " + y + ", " + z + ") is " + (powered ? "powered" : "not powered") + " at tick " + tick);
    }

    @Override
    public void update(boolean newState) {
        this.setPowered(newState, 0);  // Mise à jour de l'état du fil en fonction du changement d'état reçu
        notifyObservers();  // Notifier tous les observateurs lorsque ce fil change d'état
    }

    public void setPowered(boolean powered, int tick) {
        this.powered = powered;
        this.addStateChange(powered, tick); // Ajouter un changement d'état
    }

    public boolean isPowered() {
        return powered;
    }

    @Override
    public void interact() {
        System.out.println("Wire at (" + x + ", " + y + ", " + z + ")");
    }

    @Override
    protected void resetState(boolean initialState) {
        this.powered = initialState;  // Réinitialiser l'état d'alimentation
    }

    // Gestion des observateurs (élément observé)
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this.powered);  // Notifier chaque observateur de l'état actuel du fil
        }
    }
}
