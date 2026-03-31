package DesignPatterns.StructuralPatterns.ProxyDesignPattern.Before;

/**
 * PROBLEM: The RealSubject is exposed directly to the client.
 * There is no middleman to handle "cross-cutting concerns" like
 * security, logging, or caching.
 */
class RealVideoDownloader {

  public String downloadVideo(String videoUrl) {
    // ISSUE 1: Heavy Operation
    // This represents a costly network call or disk I/O.
    // Without a proxy, we perform this every single time.

    // ISSUE 2: Missing Access Control
    // Any user can call this method. There is no logic to check
    // if the user is a "Premium" member or has a valid API key.

    // ISSUE 3: Missing Redundancy Check (Caching)
    // If 100 users ask for the same viral video, we download it 100 times.

    System.out.println("Downloading video from URL: " + videoUrl);
    return "Video content from " + videoUrl;
  }
}

public class Main {

  public static void main(String[] args) {
    // Client interacts directly with the expensive resource.
    RealVideoDownloader realVideoDownloader = new RealVideoDownloader();

    // Request 1: Remote download happens.
    realVideoDownloader.downloadVideo("https://video.com/proxy-pattern");

    // Request 2: The exact same URL!
    // PROBLEM: We are re-downloading because the class has no memory/cache.
    realVideoDownloader.downloadVideo("https://video.com/proxy-pattern");
  }
}
