package observer;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(); // Notifier tous les observateurs du changement d'état
}