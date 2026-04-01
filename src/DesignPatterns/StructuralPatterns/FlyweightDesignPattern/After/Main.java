package DesignPatterns.StructuralPatterns.FlyweightDesignPattern.Good;

import java.util.*;

/**
 * 1. THE FLYWEIGHT (Intrinsic State)
 * This class stores the data that is constant across many objects.
 * We only want one instance of 'Oak' in memory, regardless of how many
 * 'Oak' trees exist in our forest.
 */
class TreeType {

    private String name;
    private String color;
    private String texture;

    public TreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    // The draw method accepts extrinsic state (x, y) as parameters
    // rather than storing them, keeping this object stateless-ish.
    public void draw(int x, int y) {
        System.out.println(
            "Drawing " + name + " tree at (" + x + ", " + y + ")"
        );
    }
}

/**
 * 2. THE CONTEXT (Extrinsic State)
 * This is a lightweight object that contains the unique data (coordinates).
 * It maintains a reference to the shared Flyweight (TreeType).
 */
class Tree {

    private int x;
    private int y;
    private TreeType treeType; // Reference to shared data

    public Tree(int x, int y, TreeType treeType) {
        this.x = x;
        this.y = y;
        this.treeType = treeType;
    }

    public void draw() {
        // Delegate rendering to the Flyweight, passing in the unique coordinates
        treeType.draw(x, y);
    }
}

/**
 * 3. THE FLYWEIGHT FACTORY
 * This manages the cache of Flyweights. It ensures that we don't
 * create a new TreeType if one already exists.
 */
class TreeFactory {

    static Map<String, TreeType> treeTypeMap = new HashMap<>();

    public static TreeType getTreeType(
        String name,
        String color,
        String texture
    ) {
        String key = name + " - " + color + " - " + texture;

        // If the type doesn't exist, create it once.
        if (!treeTypeMap.containsKey(key)) {
            System.out.println("Creating new TreeType for: " + name);
            treeTypeMap.put(key, new TreeType(name, color, texture));
        }
        // Otherwise, return the existing shared instance.
        return treeTypeMap.get(key);
    }
}

class Forest {

    private List<Tree> trees = new ArrayList<>();

    public void plantTree(
        int x,
        int y,
        String name,
        String color,
        String texture
    ) {
        // Key logic: We fetch a shared type from the factory
        TreeType type = TreeFactory.getTreeType(name, color, texture);
        Tree tree = new Tree(x, y, type);
        trees.add(tree);
    }

    public void draw() {
        for (Tree tree : trees) {
            tree.draw();
        }
    }
}

class Main {

    public static void main(String[] args) {
        Forest forest = new Forest();

        // 1 Million Trees, but only ONE 'Oak' TreeType object is created.
        for (int i = 0; i < 1000000; i++) {
            forest.plantTree(i, i, "Oak", "Green", "Rough");
        }

        System.out.println("Planted 1 million trees.");
        System.out.println(
            "Number of TreeType objects in memory: " +
                TreeFactory.treeTypeMap.size()
        );
    }
}
