package factory.creators;

import java.util.HashMap;

import elements.Element;

public interface ElementCreator {
    Element createElement(int x, int y, int z, String[] args, HashMap<String, String> namedArgs);
}