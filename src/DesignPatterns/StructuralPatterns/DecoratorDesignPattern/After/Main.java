package DesignPatterns.StructuralPatterns.DecoratorDesignPattern.After;

/**
 * 1. THE COMPONENT INTERFACE
 * Defines the basic operations for both the objects being decorated
 * and the decorators themselves.
 */
interface Pizza {
    String getDescription();
    double getCost();
}

/**
 * 2. CONCRETE COMPONENTS
 * These are the base objects that can be decorated.
 * Notice they don't know anything about toppings yet.
 */
class PlainPizza implements Pizza {

    @Override
    public String getDescription() {
        return "Plain Pizza";
    }

    @Override
    public double getCost() {
        return 150.00;
    }
}

class MargheritaPizza implements Pizza {

    @Override
    public String getDescription() {
        return "Margherita Pizza";
    }

    @Override
    public double getCost() {
        return 200.00;
    }
}

/**
 * 3. THE ABSTRACT DECORATOR
 * Key Concept: It implements the 'Pizza' interface AND contains a 'Pizza' object.
 * This "HAS-A" relationship allows us to wrap any Pizza (base or another decorator).
 */
abstract class PizzaDecorator implements Pizza {

    protected Pizza pizza; // The "wrapped" object

    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }
}

/**
 * 4. CONCRETE DECORATORS
 * These classes add specific behavior (toppings/costs) to the wrapped pizza.
 */
class ExtraCheese extends PizzaDecorator {

    public ExtraCheese(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        // We delegate to the wrapped object and then append our own info
        return pizza.getDescription() + ", Extra Cheese";
    }

    @Override
    public double getCost() {
        // We delegate the base cost calculation and then add our own price
        return pizza.getCost() + 40.0;
    }
}

class Olives extends PizzaDecorator {

    public Olives(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Olives";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 30.0;
    }
}

class StuffedCrust extends PizzaDecorator {

    public StuffedCrust(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Stuffed Crust";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 50.0;
    }
}

public class Main {

    public static void main(String[] args) {
        // FLEXIBILITY: We can now build a pizza like an onion skin.
        // Base layer: Margherita
        Pizza myPizza = new MargheritaPizza();

        // Layer 2: Wrap it in Cheese
        myPizza = new ExtraCheese(myPizza);

        // Layer 3: Wrap it in Olives
        myPizza = new Olives(myPizza);

        // Layer 4: Wrap it in Stuffed Crust
        myPizza = new StuffedCrust(myPizza);

        // This recursive delegation calculates the total description and cost perfectly.
        System.out.println("Final Order: " + myPizza.getDescription());
        System.out.println("Total Bill: ₹" + myPizza.getCost());
    }
}
