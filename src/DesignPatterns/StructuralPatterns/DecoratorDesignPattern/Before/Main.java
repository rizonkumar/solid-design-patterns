package DesignPatterns.StructuralPatterns.DecoratorDesignPattern.Before;

/**
 * PROBLEM: Combinatorial Explosion.
 * This design relies on inheritance to add features (toppings).
 */
class PlainPizza {}

// Adding one topping: Requires a new class.
class CheesePizza extends PlainPizza {}

class OlivePizza extends PlainPizza {}

class StuffedPizza extends PlainPizza {}

// Adding two toppings: Requires even more classes to cover every possible combination.
class CheeseStuffedPizza extends CheesePizza {}

class CheeseOlivePizza extends CheesePizza {}

/**
 * PROBLEM: Exponential Growth.
 * If you add a 4th topping (like Mushroom), you would need to create classes for:
 * MushroomPizza, CheeseMushroomPizza, OliveMushroomPizza, CheeseOliveMushroomPizza, etc.
 */
class CheeseOliveStuffedPizza extends CheeseOlivePizza {}

public class Main {

    public static void main(String[] args) {
        // Base pizza
        PlainPizza plainPizza = new PlainPizza();

        // ISSUES:
        // 1. Static Nature: You cannot add a topping to an existing object at runtime.
        // 2. Rigid Structure: If you want to change the price of 'Cheese', you have to
        //    update every single class that extends or includes Cheese.
        // 3. Maintenance: It is nearly impossible to maintain 20+ classes for simple pizza variations.

        CheeseOliveStuffedPizza cheeseOliveStuffedPizza =
            new CheeseOliveStuffedPizza();
    }
}
