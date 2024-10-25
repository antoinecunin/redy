package elements;

import java.util.ArrayList;
import java.util.List;

import observer.Observer;
import observer.Subject;

public class Repeater extends Element implements Observer, Subject {
    private int delay;
    private String orientation;
    private boolean powered;
    private List<Observer> observers;

    public Repeater(int x, int y, int z, int delay, String orientation) {
        super(x, y, z);
        this.delay = delay;
        this.orientation = orientation;
        this.powered = false;
        this.observers = new ArrayList<>();
        this.addStateChange(powered, 0);
    }

    public String getOrientation() {
        return orientation;
    }

    @Override
    public void update(int tick) {
        System.out.println("Repeater at (" + x + ", " + y + ", " + z + ") is " + (powered ? "powered" : "not powered") + " at tick " + tick + " with delay " + delay);
    }

    @Override
    public void update(boolean newState) {
        this.setPowered(newState, 0);  // Mise à jour de l'état en fonction du signal reçu
        notifyObservers();  // Propager le signal aux éléments en aval
    }

    public void setPowered(boolean powered, int tick) {
        this.powered = powered;
        this.addStateChange(powered, tick);  // Ajouter un changement d'état
    }

    public boolean isPowered() {
        return powered;
    }

    @Override
    public void interact() {
        System.out.println("Repeater at (" + x + ", " + y + ", " + z + ") with delay " + delay + " facing " + orientation);
    }

    @Override
    protected void resetState(boolean initialState) {
        this.powered = initialState;  // Réinitialiser l'état du répéteur
    }

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
            observer.update(this.powered);  // Notifier chaque observateur de l'état actuel
        }
    }
}
