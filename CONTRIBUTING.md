# Coding Standards and Repository Guidelines

## Table of Contents
- [Coding Standards and Repository Guidelines](#coding-standards-and-repository-guidelines)
  - [Table of Contents](#table-of-contents)
    - [1. Commit Conventions](#1-commit-conventions)
      - [Examples](#examples)
    - [2. Branching Strategy](#2-branching-strategy)
      - [Exemples](#exemples)
    - [3. Code Style](#3-code-style)
    - [4. File and Folder Structure](#4-file-and-folder-structure)
    - [5. Documentation Standards](#5-documentation-standards)
    - [6. Pull Request Guidelines](#6-pull-request-guidelines)

---

### 1. Commit Conventions

Follow the **Conventional Commits** format to keep a consistent commit history. The format is:

**`type(scope): description`**

- **type**:
  - `feat`: for new features
  - `fix`: for bug fixes
  - `docs`: for documentation changes
  - `style`: for code style changes (formatting, indentation, etc.) with no functional impact
  - `refactor`: for code restructuring without changing functionality
  - `test`: for adding or modifying tests
  - `chore`: for other changes, like updating dependencies

- **scope**: Specify the part of the codebase affected, such as `parser`, `simulation`, `UI`, etc.

- **description**: Write a concise summary in the present tense, e.g., `feat(parser): add support for new redstone element`.

#### Examples
```plaintext
feat(simulation): implement basic tick-based system
fix(parser): handle comments in the input file
docs(readme): add project overview and setup instructions
```

### 2. Branching Strategy

We use a **feature branching strategy**:
- **main**: The stable branch, ready for deployment. Only merge into `main` after thorough testing.
- **develop**: The main integration branch for ongoing work. Branch out from `develop` for feature and bug fix branches.
- **feature/[feature-name]**: For new features. Branch from `develop`.
- **bugfix/[bug-name]**: For bug fixes. Branch from `develop`.

#### Exemples
```plaintext
git checkout -b feature/parser-support
```

### 3. Code Style

Maintain consistent and readable code. We follow these guidelines:

- **Indentation**: Use 4 spaces for Java, no tabs.
- **Naming Conventions**:
  - **Classes**: `PascalCase`, e.g., `RedstoneSimulator`.
  - **Methods/Functions**: `camelCase`, e.g., `parseElement`.
  - **Variables**: `camelCase`, e.g., `currentTick`.
  - **Constants**: `UPPER_SNAKE_CASE`, e.g., `DEFAULT_TICK_RATE`.
- **Brackets and Spacing**: 
  - Use braces `{}` for all control structures, even if a single statement follows.
  - Keep spacing around operators and after commas for readability.

### 4. File and Folder Structure

Organize files logically for easy navigation. Here’s the suggested structure (not yet updated):

```plaintext
diags/
docs/
examples/
src/
    ├── java/
    ├── resources/
        ├── assets/
tests/
.github/
    └── workflows/
```

### 5. Documentation Standards

- **README.md**: Each repository must have a `README.md` with:
  - Project overview and goals
  - Installation instructions
  - Basic usage guide
  - Contribution guidelines

- **In-line Documentation**:
  - Write concise Javadoc for all public classes and methods.
  - Comment complex or non-intuitive code sections.

### 6. Pull Request Guidelines

Follow these steps for each pull request:

- Ensure your branch is up-to-date with `develop`.
- **Check the code**:
  - Run tests and verify they pass.
  - Ensure no warnings or errors in the code.
- **Pull Request Message**: Write a descriptive title and summary.
  - Example: `feat(parser): add support for negative coordinates`
- **Review**: All pull requests require at least one approval before merging.
- 
---

These guidelines will help maintain a clean, organized, and professional repository. Feel free to reach out with questions or suggestions to improve these standards.