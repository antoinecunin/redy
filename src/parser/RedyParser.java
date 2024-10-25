package parser;

import elements.Element;
import elements.SolidBlock;
import factory.ElementFactory;
import utils.ArgumentParser;
import exceptions.OutOfBoundsException;
import exceptions.DuplicateElementException;
import exceptions.MissingBlockSupportException;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RedyParser {
    private static final Pattern PATTERN_FULL = Pattern.compile(
        "(\\w+)\\(([^)]+)\\)" // Capturer les éléments du style lever(x=1, y=1, z=1, state=on)
    );

    public HashMap<String, Element> parse(String filename) {
        HashMap<String, Element> elements = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // Ignorer les lignes vides ou les commentaires
                if (line.isEmpty() || line.startsWith("#")) continue;

                Matcher matcher = PATTERN_FULL.matcher(line);
                if (matcher.matches()) {
                    String elementType = matcher.group(1); // ex: lever, repeater
                    String args = matcher.group(2);        // ex: x=1, y=1, z=1, state=on

                    try {
                        // Traiter les arguments et créer l'élément correspondant
                        processElement(elementType, args, elements);
                    } catch (OutOfBoundsException | DuplicateElementException | MissingBlockSupportException e) {
                        System.err.println(e.getMessage()); // Affiche l'erreur correspondante
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return elements;
    }

    private void processElement(String elementType, String args, HashMap<String, Element> elements)
    throws OutOfBoundsException, DuplicateElementException, MissingBlockSupportException {

        HashMap<String, String> argMap = new HashMap<>();
        StringTokenizer tokenizer = new StringTokenizer(args, ", ");
        String[] positionalArgs = new String[5]; // Ajusté pour plus d'arguments

        int positionalIndex = 0;
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();

            if (token.contains("=")) {
                String[] parts = token.split("=");
                argMap.put(parts[0], parts[1]); // Stocker les arguments nommés
            } else if (positionalIndex < positionalArgs.length) {
                positionalArgs[positionalIndex++] = token; // Stocker les arguments positionnels
            }
        }

        // Extraire les coordonnées x, y, z
        int x = Integer.parseInt(positionalArgs[0] != null ? positionalArgs[0] : argMap.get("x"));
        int y = Integer.parseInt(positionalArgs[1] != null ? positionalArgs[1] : argMap.get("y"));
        int z = Integer.parseInt(positionalArgs[2] != null ? positionalArgs[2] : argMap.get("z"));

        // Vérification des coordonnées hors limites
        if (x < 0 || y < 0 || z < 0) {
            throw new OutOfBoundsException("Erreur : Les coordonnées négatives sont interdites. Élement aux coordonnées (" + x + ", " + y + ", " + z + ").");
        }

        String key = getKey(x, y, z);

        // Vérification : Si un élément existe déjà à ces coordonnées, signaler le conflit
        if (elements.containsKey(key)) {
            throw new DuplicateElementException("Conflit détecté : un élément existe déjà aux coordonnées (" + x + ", " + y + ", " + z + ").");
        }

        // Traitement spécial pour le levier : vérifier si sa position est correcte
        if (elementType.equals("lever")) {
            String position = ArgumentParser.parseStringValue(argMap, positionalArgs, "position", 3, "top");

            // Vérification si le levier est sur un bloc solide ou sur un côté correct
            if (!isProperlyAttached(x, y, z, position, elements)) {
                throw new MissingBlockSupportException("Erreur : Le levier doit être placé sur un bloc solide ou un côté valide aux coordonnées (" + x + ", " + y + ", " + z + ").");
            }
        } 
        // Vérification de la conformité des éléments pour les éléments nécessitant un support solide
        else if (needsBaseSupport(elementType) && !isBlockBelow(x, y, z, elements)) {
            throw new MissingBlockSupportException("Erreur : L'élément " + elementType + " doit être placé sur un bloc solide aux coordonnées (" + x + ", " + (y - 1) + ", " + z + ").");
        }

        // Créer l'élément si aucune erreur
        Element element = ElementFactory.createElement(elementType, x, y, z, positionalArgs, argMap);
        elements.put(key, element);
    }

    // Vérifie si un levier est bien placé sur un bloc solide ou sur un côté
    private boolean isProperlyAttached(int x, int y, int z, String position, HashMap<String, Element> elements) {
        switch (position) {
            case "top":
                return isBlockBelow(x, y, z, elements); // Le levier est sur le dessus du bloc
            case "north":
                return isBlockAdjacent(x, y, z - 1, elements); // Bloc solide au nord
            case "south":
                return isBlockAdjacent(x, y, z + 1, elements); // Bloc solide au sud
            case "east":
                return isBlockAdjacent(x + 1, y, z, elements); // Bloc solide à l'est
            case "west":
                return isBlockAdjacent(x - 1, y, z, elements); // Bloc solide à l'ouest
            default:
                return false;
        }
    }

    // Vérifie si un bloc est adjacent aux coordonnées spécifiées (pour les côtés)
    private boolean isBlockAdjacent(int x, int y, int z, HashMap<String, Element> elements) {
        String blockKey = getKey(x, y, z);
        Element adjacentElement = elements.get(blockKey);
        return adjacentElement instanceof SolidBlock; // Retourne true si un bloc solide est adjacent
    }

    // Vérifie si un élément (repeater, wire) doit être posé sur un bloc solide
    private boolean needsBaseSupport(String elementType) {
        return elementType.equals("repeater") || elementType.equals("wire");
    }

    // Vérifie s'il y a un bloc solide sous l'élément aux coordonnées spécifiées
    private boolean isBlockBelow(int x, int y, int z, HashMap<String, Element> elements) {
        if (y == 0) { // Si y == 0, on considère que le bloc en dessous est solide (le sol)
            return true;
        }

        String blockKey = getKey(x, y - 1, z);
        Element elementBelow = elements.get(blockKey);
        return elementBelow instanceof SolidBlock; // Retourne true si un bloc solide est présent en dessous
    }


    // Méthode pour créer une clé unique pour les coordonnées (x, y, z)
    private String getKey(int x, int y, int z) {
        return x + "," + y + "," + z;
    }
}
