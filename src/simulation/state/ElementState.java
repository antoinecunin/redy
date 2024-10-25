package simulation.state;

public class ElementState {
    private boolean state;    // On/Off ou activé/désactivé
    private int tickStart;    // Tick où l'état a commencé
    private int tickEnd;      // Tick où l'état a pris fin

    public ElementState(boolean state, int tickStart) {
        this.state = state;
        this.tickStart = tickStart;
        this.tickEnd = -1;    // Indique que l'état est toujours en cours
    }

    public boolean getState() {
        return state;
    }

    public int getTickStart() {
        return tickStart;
    }

    public int getTickEnd() {
        return tickEnd;
    }

    public void setTickEnd(int tickEnd) {
        this.tickEnd = tickEnd;
    }
}
