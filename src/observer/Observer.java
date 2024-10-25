package observer;

public interface Observer {
    void update(boolean newState); // La méthode pour réagir aux changements
    void update(int tick); // La méthode pour réagir aux changements à chaque tick
}