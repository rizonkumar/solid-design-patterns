package DesignPatterns.StructuralPatterns.FlyweightDesignPattern.Before;

import java.util.*;

/**
 * PROBLEM: Redundant Data Storage.
 * Every 'Tree' object carries its own 'name', 'color', and 'texture'.
 * In a forest of 1 million trees, we store the string "Oak" 1 million times!
 */
class Tree {

    // Extrinsic State: Context-dependent (Changes for every tree)
    private int x;
    private int y;

    // Intrinsic State: Context-independent (Constant for all trees of this type)
    // ISSUE: These are duplicated in every single object instance.
    private String name;
    private String color;
    private String texture;

    public Tree(int x, int y, String name, String color, String texture) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    public void draw() {
        System.out.println(
            "Drawing tree at (" + x + ", " + y + ") with type " + name
        );
    }
}

/**
 * PROBLEM: Inefficient Collection.
 * The 'Forest' is holding 1 million 'Tree' objects.
 * Each object has metadata overhead (headers, pointers) plus the redundant strings.
 */
class Forest {

    private List<Tree> trees = new ArrayList<>();

    public void plantTree(
        int x,
        int y,
        String name,
        String color,
        String texture
    ) {
        // ISSUE: 1,000,000 instances of 'Tree' are created.
        // This will likely cause an OutOfMemoryError in a real high-fidelity simulation.
        Tree tree = new Tree(x, y, name, color, texture);
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

        // SCENARIO: A game engine or a map simulation.
        // If an 'Oak' type takes 1KB of memory, 1 million trees = 1GB of RAM.
        // If we have 10 types of trees, we shouldn't be replicating type data.
        for (int i = 0; i < 1000000; i++) {
            forest.plantTree(i, i, "Oak", "Green", "Rough");
        }

        System.out.println("Planted 1 million trees.");
    }
}
