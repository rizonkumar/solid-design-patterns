package DesignPatterns.StructuralPatterns.BridgeDesignPattern.Good;

import java.util.*;

/**
 * 1. THE IMPLEMENTOR INTERFACE
 * This defines the "how" (rendering/loading logic).
 * It can vary independently from the platforms.
 */
interface VideoQuality {
  void load(String title);
}

/**
 * 2. CONCRETE IMPLEMENTORS
 * These handle the specific technical details of each quality level.
 */
class SDQuality implements VideoQuality {

  public void load(String title) {
    System.out.println("Streaming " + title + " in SD Quality");
  }
}

class HDQuality implements VideoQuality {

  public void load(String title) {
    System.out.println("Streaming " + title + " in HD Quality");
  }
}

class UltraHDQuality implements VideoQuality {

  public void load(String title) {
    System.out.println("Streaming " + title + " in 4K Ultra HD Quality");
  }
}

/**
 * 3. THE ABSTRACTION
 * This defines the "what" (the high-level entity).
 * KEY BRIDGE: It HAS-A VideoQuality instead of being one.
 */
abstract class VideoPlayer {

  // This protected reference is the "Bridge" to the implementation
  protected VideoQuality quality;

  public VideoPlayer(VideoQuality quality) {
    this.quality = quality;
  }

  public abstract void play(String title);
}

/**
 * 4. REFINED ABSTRACTIONS
 * These are specific versions of the abstraction (different platforms).
 */
class WebPlayer extends VideoPlayer {

  public WebPlayer(VideoQuality quality) {
    super(quality);
  }

  public void play(String title) {
    System.out.println("Web Platform:");
    // Delegating the work to the implementation side of the bridge
    quality.load(title);
  }
}

class MobilePlayer extends VideoPlayer {

  public MobilePlayer(VideoQuality quality) {
    super(quality);
  }

  public void play(String title) {
    System.out.println("Mobile Platform:");
    quality.load(title);
  }
}

// Client Code
class Main {

  public static void main(String[] args) {
    // FLEXIBILITY: We can mix and match any platform with any quality.

    // Combination 1: Web + HD
    VideoPlayer player1 = new WebPlayer(new HDQuality());
    player1.play("Interstellar");

    System.out.println();

    // Combination 2: Mobile + Ultra HD
    // Notice we didn't need a "MobileUltraHDPlayer" class!
    VideoPlayer player2 = new MobilePlayer(new UltraHDQuality());
    player2.play("Inception");

    // RUNTIME SWITCHING: We could easily change player2's quality
    // simply by swapping the 'quality' object, no new class needed.
  }
}
