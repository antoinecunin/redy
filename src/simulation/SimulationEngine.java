package simulation;

import elements.Element;
import elements.Lamp;
import elements.Lever;
import elements.Wire;
import elements.Repeater;
import elements.SolidBlock;

import java.util.HashMap;

public class SimulationEngine {
    private HashMap<String, Element> elements;
    private int tickCount = 0;

    public SimulationEngine(HashMap<String, Element> elements) {
        this.elements = elements;
        initializeObservers();  // Initialiser les relations entre observateurs et sujets
    }

    // Assigner les observateurs à leurs sujets (par exemple, les fils de redstone aux leviers)
    private void initializeObservers() {
        Lever lever1 = (Lever) elements.get("lever1");
        Wire wire1 = (Wire) elements.get("wire1");
        Wire wire2 = (Wire) elements.get("wire2");
        Repeater repeater1 = (Repeater) elements.get("repeater1");
        SolidBlock solidBlock1 = (SolidBlock) elements.get("solidBlock1");
        Repeater repeater2 = (Repeater) elements.get("repeater2");
        Lamp lamp1 = (Lamp) elements.get("lamp1");
        Repeater repeater3 = (Repeater) elements.get("repeater3");

        lever1.addObserver(wire1);  // Le fil observe le levier
        wire1.addObserver(wire2);  // Un deuxième fil observe le premier fil
        wire2.addObserver(repeater1);  // Le répéteur observe le deuxième fil
        repeater1.addObserver(solidBlock1);  // Le bloc solide observe le répéteur
        solidBlock1.addObserver(repeater2);  // Le deuxième répéteur observe le bloc solide
        repeater2.addObserver(lamp1);  // La lampe observe le deuxième répéteur
        lamp1.addObserver(repeater3);  // Le troisième répéteur observe la lampe
    }

    public void runSimulation(int maxTicks) {
        for (int i = 0; i < maxTicks; i++) {
            System.out.println("Tick " + tickCount);
            updateElements();
            tickCount++;
        }
    }

    private void updateElements() {
        for (Element element : elements.values()) {
            element.update(tickCount);
        }
    }

    public void resetSimulation() {
        for (Element element : elements.values()) {
            element.resetToInitialState();
        }
        tickCount = 0;
    }
}
