package restaurant;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Menu {
    private final Map<String, MenuItem> menuItems = new HashMap<>();

    // Adds a new item to the menu
    public void addItem(MenuItem item) {
        menuItems.put(item.getName(), item);
    }

    public MenuItem getItem(String name) {
        return menuItems.get(name);
    }

    public Map<String, MenuItem> getMenuItems() {
        return Collections.unmodifiableMap(menuItems);
    }
}
