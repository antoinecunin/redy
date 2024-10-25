package factory.creators;

import elements.Repeater;
import elements.Element;
import utils.ArgumentParser;
import java.util.HashMap;

public class RepeaterCreator implements ElementCreator {

    @Override
    public Element createElement(int x, int y, int z, String[] args, HashMap<String, String> namedArgs) {
        int delay = ArgumentParser.parseIntValue(namedArgs, args, "delay", 3, 1);
        String orientation = ArgumentParser.parseStringValue(namedArgs, args, "orientation", 4, "north");
        return new Repeater(x, y, z, delay, orientation);
    }
}
