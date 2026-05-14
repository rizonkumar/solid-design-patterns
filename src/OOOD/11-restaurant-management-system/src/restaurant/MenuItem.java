package restaurant;

import java.math.BigDecimal;

// Represents a single item available on the restaurant menu
public class MenuItem {
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final Category category;

    public MenuItem(String name, String description, BigDecimal price, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public BigDecimal getPrice() { return price; }
    public Category getCategory() { return category; }

    // Enumeration of possible menu item categories
    public enum Category {
        MAIN,
        APPETIZER,
        DESSERT
    }
}
