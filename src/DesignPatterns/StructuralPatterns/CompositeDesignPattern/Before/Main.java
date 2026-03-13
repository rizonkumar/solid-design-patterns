package DesignPatterns.StructuralPatterns.CompositeDesignPattern.Before;

import java.util.*;

/**
 * PROBLEM 1: Lack of a Common Interface.
 * There is no 'CatalogComponent' or 'Component' interface. 
 * This prevents treating single items and bundles uniformly.
 */
class Product {
    private String name;
    private double price;
    
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void display(String indent) {
        System.out.println(indent + "Product: " + name + " - ₹" + price);
    }
}

/**
 * PROBLEM 2: Rigid Composition.
 * This class can only store 'Product' objects. It cannot store other bundles.
 */
class ProductBundle {
    private String bundleName;
    private List<Product> products = new ArrayList<>();
    
    public ProductBundle(String bundleName) {
        this.bundleName = bundleName;
    }

    // This method is locked to the concrete 'Product' class.
    public void addProduct(Product product) {
        products.add(product);
    }

    public double getPrice() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public void display(String indent) {
        System.out.println(indent + "Bundle: " + bundleName);
        for (Product product : products) {
            product.display(indent + "  ");
        }
    }
}

class Main {
    public static void main(String[] args) {
        Product book = new Product("Book", 500);
        Product charger = new Product("Charger", 800);
        
        ProductBundle iphoneCombo = new ProductBundle("iPhone Combo Pack");
        iphoneCombo.addProduct(charger);

        /**
         * PROBLEM 3: Type-Safety & Code Smells.
         * We have to use List<Object> because there is no common type.
         */
        List<Object> cart = new ArrayList<>();
        cart.add(book);
        cart.add(iphoneCombo);
        
        double total = 0;
        System.out.println("Cart Details:\n");

        /**
         * PROBLEM 4: Violation of Open/Closed Principle.
         * If we add a new type (e.g., 'DigitalSubscription'), we must 
         * come back here and add another 'instanceof' block.
         */
        for (Object item : cart) {
            if (item instanceof Product) {
                ((Product) item).display("  ");
                total += ((Product) item).getPrice();
            } else if (item instanceof ProductBundle) {
                ((ProductBundle) item).display("  ");
                total += ((ProductBundle) item).getPrice();
            }
        }

        System.out.println("\nTotal Price: ₹" + total);
    }
}