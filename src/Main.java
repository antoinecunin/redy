import elements.*;
import simulation.SimulationEngine;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        // Initialiser les éléments du circuit redstone
        HashMap<String, Element> elements = new HashMap<>();
        
        // Ajouter des éléments au système
        Lever lever1 = new Lever(1, 1, 1, false, "top");  // Un levier désactivé
        Wire wire1 = new Wire(2, 1, 1);                   // Un fil de redstone
        Wire wire2 = new Wire(3, 1, 1);                   // Un autre fil de redstone
        Repeater repeater1 = new Repeater(4, 1, 1, 1, "south"); // Un répéteur avec un délai de 1 tick
        SolidBlock solidBlock1 = new SolidBlock(5, 1, 1); // Un bloc solide
        Repeater repeater2 = new Repeater(6, 1, 1, 2, "south"); // Un autre répéteur avec un délai de 2 ticks
        Lamp lamp1 = new Lamp(7, 1, 1);                   // Une lampe
        Repeater repeater3 = new Repeater(8, 1, 1, 3, "south"); // Un troisième répéteur avec un délai de 3 ticks

        // Ajouter les éléments à la HashMap
        elements.put("lever1", lever1);
        elements.put("wire1", wire1);
        elements.put("wire2", wire2);
        elements.put("repeater1", repeater1);
        elements.put("solidBlock1", solidBlock1);
        elements.put("repeater2", repeater2);
        elements.put("lamp1", lamp1);
        elements.put("repeater3", repeater3);

        // Créer l'instance du moteur de simulation
        SimulationEngine engine = new SimulationEngine(elements);

        // Exécuter la simulation pour 10 ticks
        engine.runSimulation(10);

        // Simuler un changement d'état (activer un levier au tick 5)
        System.out.println("\nActivating lever at tick 5...");
        lever1.toggle(5);  // Le levier est activé au tick 5

        // Relancer la simulation pour 10 ticks supplémentaires
        engine.runSimulation(10);

        // Réinitialiser la simulation
        System.out.println("\nResetting the simulation to initial state...");
        engine.resetSimulation();
        engine.runSimulation(5);
    }
}
