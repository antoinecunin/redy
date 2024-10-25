package factory.creators;

import java.util.HashMap;

import elements.Element;
import elements.Lamp;

public class LampCreator implements ElementCreator {
    
    @Override
    public Element createElement(int x, int y, int z, String[] args, HashMap<String, String> namedArgs) {
        return new Lamp(x, y, z);
    }
}
