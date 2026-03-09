package DesignPatterns.CreationalPatterns.BuilderPattern.Before;

import java.util.List;

/**
 * LEGACY APPROACH: Using a "Telescoping Constructor"
 * This file demonstrates the problems that arise before implementing the Builder Pattern.
 */

class BurgerMeal {
    private String bread;
    private String patty;
    private String sides;
    private List<String> drinks;

    // PROBLEM 1: Rigidity
    // If we add 'cheese' or 'sauce', every single call to this constructor across 
    // the entire project will break and must be updated.
    public BurgerMeal(String bread, String patty, String sides, List<String> drinks) {
        this.bread = bread;
        this.patty = patty;
        this.sides = sides;
        this.drinks = drinks;
    }

    @Override
    public String toString() {
        return "Burger [Bread=" + bread + ", Patty=" + patty + ", Sides=" + sides + ", Drinks=" + drinks + "]";
    }
}

public class BurgerMealWithoutBuilder {
    public static void main(String[] args) {
        // PROBLEM 2: Readability
        // Looking at the line below, it's hard to tell which String is 'bread' and which is 'sides' 
        // without looking at the class definition.
        
        // PROBLEM 3: Forced Arguments
        // Even if I don't want sides or drinks, I am forced to pass "none" or null.
        BurgerMeal burgerMeal = new BurgerMeal("wheat", "non-veg", "none", null);  
        
        System.out.println(burgerMeal);
    }
}