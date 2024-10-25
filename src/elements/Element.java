package elements;

import java.util.ArrayList;
import java.util.List;

import simulation.state.ElementState;

public abstract class Element {
    protected int x, y, z;
    protected List<ElementState> stateHistory;

    public Element(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.stateHistory = new ArrayList<>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    // Méthode à implémenter pour chaque élément qui gère ses mises à jour d'état
    public abstract void update(int tick);

    // Méthode pour ajouter un nouvel état avec le tick de changement
    public void addStateChange(boolean newState, int tick) {
        // Si l'état a changé, ajouter un nouvel état avec le tick actuel
        ElementState currentState = getCurrentState();
        if (currentState == null || currentState.getState() != newState) {
            if (currentState != null) {
                currentState.setTickEnd(tick); // Mettre fin à l'état précédent
            }
            stateHistory.add(new ElementState(newState, tick)); // Ajouter un nouvel état
        }
    }

    // Retourne le dernier état de l'élément
    public ElementState getCurrentState() {
        return stateHistory.isEmpty() ? null : stateHistory.get(stateHistory.size() - 1);
    }

    // Réinitialise l'élément à son état initial
    public void resetToInitialState() {
        if (!stateHistory.isEmpty()) {
            ElementState initialState = stateHistory.get(0);
            resetState(initialState.getState());  // Réinitialiser l'état interne de l'élément
            stateHistory.clear();
            stateHistory.add(initialState);
        }
    }

    // Méthode pour réinitialiser l'état interne d'un élément
    protected abstract void resetState(boolean initialState);

    public abstract void interact(); // Cette méthode sera implémentée dans chaque élément
}
