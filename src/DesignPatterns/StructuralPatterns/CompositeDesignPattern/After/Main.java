package DesignPatterns.StructuralPatterns.CompositeDesignPattern.After;

import java.util.*;

/**
 * 1. THE COMPONENT INTERFACE
 * This is the heart of the pattern. It defines the common operations for
 * both simple items (Leaf) and groups of items (Composite).
 */
interface CartItem {
  double getPrice();
  void display(String indent);
}

/**
 * 2. THE LEAF
 * Represents individual products. It implements the interface methods
 * directly without knowing about any other items.
 */
class Product implements CartItem {

  private String name;
  private double price;

  public Product(String name, double price) {
    this.name = name;
    this.price = price;
  }

  @Override
  public double getPrice() {
    return price;
  }

  @Override
  public void display(String indent) {
    System.out.println(indent + "Product: " + name + " - ₹" + price);
  }
}

/**
 * 3. THE COMPOSITE
 * Represents a bundle or collection.
 * Key Concept: It contains a list of 'CartItem' (the interface), not 'Product'.
 * This allows it to hold both Products AND other ProductBundles.
 */
class ProductBundle implements CartItem {

  private String bundleName;
  // Uniformity: Holds any CartItem (Leaf or another Composite)
  private List<CartItem> items = new ArrayList<>();

  public ProductBundle(String bundleName) {
    this.bundleName = bundleName;
  }

  public void addItem(CartItem item) {
    items.add(item);
  }

  @Override
  public double getPrice() {
    double total = 0;
    // Delegation: The bundle doesn't calculate its own price;
    // it asks each child item for its price and sums them up.
    for (CartItem item : items) {
      total += item.getPrice();
    }
    return total;
  }

  @Override
  public void display(String indent) {
    System.out.println(indent + "Bundle: " + bundleName);
    for (CartItem item : items) {
      // Recursive call: Works regardless of how deep the tree goes
      item.display(indent + "  ");
    }
  }
}

/**
 * 4. THE CLIENT
 * Uses polymorphism to interact with items.
 */
class Main {

  public static void main(String[] args) {
    // Treat everything as a 'CartItem'
    CartItem book = new Product("Atomic Habits", 499);

    // Create a bundle and add items
    ProductBundle iphoneCombo = new ProductBundle("iPhone Essentials Combo");
    iphoneCombo.addItem(new Product("iPhone 15", 79999));
    iphoneCombo.addItem(new Product("AirPods", 15999));

    // TYPE SAFETY: No more List<Object>.
    // We use the common interface type.
    List<CartItem> cart = new ArrayList<>();
    cart.add(book);
    cart.add(iphoneCombo);

    System.out.println("Your Amazon Cart:");
    double total = 0;

    // NO INSTANCEOF: Polymorphism handles the call correctly.
    // Whether it's a Product or a Bundle, we just call display() and getPrice().
    for (CartItem item : cart) {
      item.display("  ");
      total += item.getPrice();
    }

    System.out.println("\nTotal: ₹" + total);
  }
}
