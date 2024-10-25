# Répertoire de sortie pour les fichiers .class
OUT_DIR = out
# Répertoire des fichiers source
SRC_DIR = src
# Fichier d'exemple à utiliser lors de l'exécution
EXAMPLE_FILE = examples/basic.redy
# Classe principale
MAIN_CLASS = Main

# Liste des fichiers Java à compiler
SOURCES = $(shell find $(SRC_DIR) -name "*.java")

# Règle par défaut (all) : compile et exécute le programme
all: compile run

# Règle pour compiler les fichiers Java
compile:
	@echo "Compiling Java files..."
	@mkdir -p $(OUT_DIR)
	@javac -d $(OUT_DIR) $(SOURCES)
	@echo "Compilation complete."

# Règle pour exécuter le programme
run:
	@echo "Running the program..."
	@java -cp $(OUT_DIR) $(MAIN_CLASS) $(EXAMPLE_FILE)

# Règle pour nettoyer les fichiers compilés
clean:
	@echo "Cleaning up..."
	@rm -rf $(OUT_DIR)
	@echo "Clean complete."

# Règle pour seulement compiler
.PHONY: compile

# Règle pour seulement exécuter
.PHONY: run

# Règle pour nettoyer les fichiers compilés
.PHONY: clean