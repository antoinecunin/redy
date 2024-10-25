package factory.creators;

import elements.SolidBlock;

import java.util.HashMap;

import elements.Element;

public class SolidBlockCreator implements ElementCreator {

    @Override
    public Element createElement(int x, int y, int z, String[] args, HashMap<String, String> namedArgs) {
        return new SolidBlock(x, y, z);
    }
}
