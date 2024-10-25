package factory.creators;

import elements.Wire;

import java.util.HashMap;

import elements.Element;

public class WireCreator implements ElementCreator {

    @Override
    public Element createElement(int x, int y, int z, String[] args, HashMap<String, String> namedArgs) {
        return new Wire(x, y, z);
    }
}
