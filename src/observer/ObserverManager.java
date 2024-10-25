package observer;

import java.util.ArrayList;
import java.util.List;

public class ObserverManager {
    private List<Observer> observers;

    public ObserverManager() {
        this.observers = new ArrayList<>();
    }

    // Ajouter un observateur
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Supprimer un observateur
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // Notifier tous les observateurs d'un changement d'état
    public void notifyObservers(boolean state) {
        for (Observer observer : observers) {
            observer.update(state);
        }
    }

    // Notifier tous les observateurs à chaque tick
    public void notifyObservers(int tick) {
        for (Observer observer : observers) {
            observer.update(tick);
        }
    }
}
