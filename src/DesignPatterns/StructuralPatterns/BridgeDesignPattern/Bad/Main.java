package DesignPatterns.StructuralPatterns.BridgeDesignPattern.Bad;

import java.util.*;

/**
 * PROBLEM: Tight Coupling between Platform and Quality.
 * Every time you add a new platform (e.g., Tablet) or a new
 * quality (e.g., 8K), you must create many new classes.
 */
interface PlayQuality {
  void play(String title);
}

// Dimension 1: Web + Dimension 2: HD
class WebHDPlayer implements PlayQuality {

  public void play(String title) {
    System.out.println("Web Player: Playing " + title + " in HD");
  }
}

// Dimension 1: Mobile + Dimension 2: HD
class MobileHDPlayer implements PlayQuality {

  public void play(String title) {
    System.out.println("Mobile Player: Playing " + title + " in HD");
  }
}

// Dimension 1: Smart TV + Dimension 2: Ultra HD
class SmartTVUltraHDPlayer implements PlayQuality {

  public void play(String title) {
    System.out.println("Smart TV: Playing " + title + " in ultra HD");
  }
}

// Dimension 1: Web + Dimension 2: 4K
class Web4KPlayer implements PlayQuality {

  public void play(String title) {
    System.out.println("Web Player: Playing " + title + " in 4K");
  }
}

class Main {

  public static void main(String[] args) {
    // ISSUE: The client is forced to use a concrete combination.
    // If you want to switch a Web player from HD to 4K, you have to
    // change the actual class instance, not just a property.
    PlayQuality player = new WebHDPlayer();
    player.play("Interstellar");
  }
}
