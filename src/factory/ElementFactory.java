package factory;

import elements.Element;
import factory.creators.*;
import java.util.HashMap;

public class ElementFactory {

    private static final HashMap<String, ElementCreator> creators = new HashMap<>();

    static {
        creators.put("lever", new LeverCreator());
        creators.put("repeater", new RepeaterCreator());
        creators.put("wire", new WireCreator());
        creators.put("lamp", new LampCreator());
        creators.put("solidBlock", new SolidBlockCreator());
    }

    public static Element createElement(String elementType, int x, int y, int z, String[] args, HashMap<String, String> namedArgs) {
        ElementCreator creator = creators.get(elementType.toLowerCase());

        if (creator == null) {
            throw new IllegalArgumentException("Unknown element type: " + elementType);
        }

        return creator.createElement(x, y, z, args, namedArgs);
    }
}
