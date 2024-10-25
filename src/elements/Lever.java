package elements;

import observer.Subject;
import observer.Observer;
import java.util.ArrayList;
import java.util.List;

public class Lever extends Element implements Subject {
    private boolean state;
    private String position;
    private List<Observer> observers;  // Liste des observateurs

    public Lever(int x, int y, int z, boolean state, String position) {
        super(x, y, z);
        this.state = state;
        this.position = position;
        this.observers = new ArrayList<>();  // Initialisation de la liste des observateurs
        this.addStateChange(state, 0);
    }

    public String getPosition() {
        return position;
    }

    @Override
    public void update(int tick) {
        System.out.println("Lever at (" + x + ", " + y + ", " + z + ") is " + (state ? "on" : "off") + " at tick " + tick);
    }

    public void toggle(int tick) {
        this.state = !this.state;
        this.addStateChange(this.state, tick);
        notifyObservers();  // Notifier les observateurs lorsque le levier est basculé
    }

    @Override
    public void interact() {
        System.out.println("Lever at (" + x + ", " + y + ", " + z + ") is " + (state ? "on" : "off") + " and placed on " + position);
    }

    @Override
    protected void resetState(boolean initialState) {
        this.state = initialState;
    }

    // Gestion des observateurs
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
            observer.update(this.state);  // Chaque observateur est notifié du nouvel état
        }
    }
}
