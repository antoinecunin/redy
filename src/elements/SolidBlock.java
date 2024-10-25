package elements;

import observer.Observer;
import observer.Subject;
import observer.ObserverManager;

public class SolidBlock extends Element implements Observer, Subject {
    private boolean conductsCurrent;
    private ObserverManager observerManager;

    public SolidBlock(int x, int y, int z) {
        super(x, y, z);
        this.conductsCurrent = false;
        this.observerManager = new ObserverManager();  // Délégation à ObserverManager
    }

    @Override
    public void update(int tick) {
        System.out.println("Solid block at (" + x + ", " + y + ", " + z + ") at tick " + tick + " is " + (conductsCurrent ? "conducting current" : "not conducting current"));
        observerManager.notifyObservers(tick);  // Notifier les observateurs à chaque tick si nécessaire
    }

    @Override
    public void update(boolean newState) {
        this.setConductsCurrent(newState, 0);
        observerManager.notifyObservers(newState);  // Utilisation de la délégation pour notifier un changement d'état
    }

    public void setConductsCurrent(boolean conductsCurrent, int tick) {
        this.conductsCurrent = conductsCurrent;
        this.addStateChange(conductsCurrent, tick);
    }

    public boolean isConductingCurrent() {
        return conductsCurrent;
    }

    @Override
    public void interact() {
        System.out.println("SolidBlock at (" + x + ", " + y + ", " + z + ") is " + (conductsCurrent ? "conducting current" : "not conducting current"));
    }

    @Override
    protected void resetState(boolean initialState) {
        this.conductsCurrent = initialState;
    }

    // Délégation des méthodes d'observateurs à ObserverManager
    @Override
    public void addObserver(Observer observer) {
        observerManager.addObserver(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerManager.removeObserver(observer);
    }

    @Override
    public void notifyObservers() {
        observerManager.notifyObservers(conductsCurrent);
    }
}
