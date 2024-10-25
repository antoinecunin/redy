package factory.creators;

import elements.Lever;
import elements.Element;
import utils.ArgumentParser;
import java.util.HashMap;

public class LeverCreator implements ElementCreator {

    @Override
    public Element createElement(int x, int y, int z, String[] args, HashMap<String, String> namedArgs) {
        boolean leverState = ArgumentParser.parseBooleanValue(namedArgs, args, "state", 4, false);
        String leverPosition = ArgumentParser.parseStringValue(namedArgs, args, "position", 3, "top");
        return new Lever(x, y, z, leverState, leverPosition);
    }
}