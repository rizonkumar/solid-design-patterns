package DesignPatterns.CreationalPatterns.BuilderPattern.After;

import java.util.List;

class BurgerMeal {
    // 1. Immutability: Use 'final' so the fields cannot be changed after the object is built.
    private final String breadType;
    private final String patty;
    private final boolean hasCheese;
    private final String side;
    private final List<String> drinks;

    // 2. Private Constructor: Prevents direct instantiation. 
    // The ONLY way to create a BurgerMeal is through the Builder.
    private BurgerMeal(BurgerMealWithBuilder builder) {
        this.breadType = builder.breadType;
        this.patty = builder.patty;
        this.hasCheese = builder.hasCheese;
        this.side = builder.side;
        this.drinks = builder.drinks;
    }

    // 3. Static Nested Class: Being 'static' means we can create the Builder 
    // without needing an existing instance of BurgerMeal.
    public static class BurgerMealWithBuilder {
        private String breadType;
        private String patty;
        private boolean hasCheese;
        private String side;
        private List<String> drinks;

        // 4. Required Parameters: The constructor of the builder enforces mandatory fields.
        public BurgerMealWithBuilder(String breadType, String patty) {
            this.breadType = breadType;
            this.patty = patty;
        }

        // 5. Fluent API: Methods return 'this' (the current builder instance)
        // allowing us to chain methods like .withCheese().withSide().
        public BurgerMealWithBuilder withCheese(boolean hasCheese) {
            this.hasCheese = hasCheese;
            return this;
        }

        public BurgerMealWithBuilder withSide(String side) {
            this.side = side;
            return this;
        }

        public BurgerMealWithBuilder withDrinks(List<String> drinks) {
            this.drinks = drinks;
            return this;
        }

        // 6. The "Build" method: Finally converts the builder into the real object.
        public BurgerMeal build() {
            return new BurgerMeal(this);
        }
    }

    @Override
    public String toString() {
        return "BurgerMeal [breadType=" + breadType + ", patty=" + patty + 
               ", hasCheese=" + hasCheese + ", side=" + side + ", drinks=" + drinks + "]";
    }
}

public class Main {
    public static void main(String[] args) {
        // Look how clean the client code is now! No more "null" values.
        BurgerMeal basic = new BurgerMeal.BurgerMealWithBuilder("wheat", "non-veg").build();
        
        BurgerMeal fullMeal = new BurgerMeal.BurgerMealWithBuilder("wheat", "non-veg")
                .withCheese(true)
                .withSide("fries")
                .withDrinks(List.of("coke"))
                .build();

        System.out.println(basic);
        System.out.println(fullMeal);
    }
}