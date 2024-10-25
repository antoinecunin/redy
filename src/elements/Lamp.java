package elements;

public class Lamp extends SolidBlock {

    public Lamp(int x, int y, int z) {
        super(x, y, z);  // Appel du constructeur de SolidBlock
    }

    @Override
    public void update(boolean newState) {
        this.setConductsCurrent(newState, 0);  // La lampe conduit du courant si elle est allumée
        System.out.println("Lamp at (" + x + ", " + y + ", " + z + ") is " + (newState ? "on and conducting current" : "off and not conducting current"));
        
        // Notifier les observateurs du changement d'état
        notifyObservers();
    }

    @Override
    public void update(int tick) {
        // Afficher l'état de la lampe (on/off) à chaque tick
        System.out.println("Lamp at (" + x + ", " + y + ", " + z + ") is " + (isConductingCurrent() ? "on and conducting current" : "off and not conducting current") + " at tick " + tick);
        
        // Notifier les observateurs à chaque tick si nécessaire
        notifyObservers();
    }
}
