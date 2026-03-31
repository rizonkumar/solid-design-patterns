package DesignPatterns.StructuralPatterns.ProxyDesignPattern.After;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. THE SUBJECT INTERFACE
 * This common interface allows the Proxy and the RealSubject to be
 * used interchangeably by the client.
 */
interface VideoDownloader {
  String downloadVideo(String videoURL);
}

/**
 * 2. THE REAL SUBJECT
 * This class contains the actual "expensive" business logic.
 * It should only be called when absolutely necessary.
 */
class RealVideoDownloader implements VideoDownloader {

  @Override
  public String downloadVideo(String videoUrl) {
    // In a real app, this would involve complex network I/O
    System.out.println("Connecting to server and downloading: " + videoUrl);
    return "High-Quality Video Data from " + videoUrl;
  }
}

/**
 * 3. THE PROXY (Virtual/Caching Proxy)
 * This class acts as a gatekeeper. It maintains a reference to the RealSubject
 * and controls access to it by implementing its own logic (Caching).
 */
class CachedVideoDownloader implements VideoDownloader {

  private RealVideoDownloader realDownloader;
  private Map<String, String> cache;

  public CachedVideoDownloader() {
    // The Proxy can manage the lifecycle of the RealSubject
    this.realDownloader = new RealVideoDownloader();
    this.cache = new HashMap<>();
  }

  @Override
  public String downloadVideo(String videoUrl) {
    // GATEKEEPING LOGIC: Check if we already have the resource
    if (cache.containsKey(videoUrl)) {
      System.out.println(
        "PROXY: Found in cache. Returning stored content for: " + videoUrl
      );
      return cache.get(videoUrl);
    }

    // DELEGATION: If not in cache, only then call the expensive RealSubject
    System.out.println(
      "PROXY: Cache miss for " + videoUrl + ". Delegating to RealDownloader..."
    );
    String video = realDownloader.downloadVideo(videoUrl);

    // POST-PROCESSING: Store the result for future requests
    cache.put(videoUrl, video);
    return video;
  }
}

/**
 * 4. THE CLIENT
 * Follows the "Program to an Interface" principle. It doesn't know
 * it's talking to a Proxy; it just knows it has a VideoDownloader.
 */
class Main {

  public static void main(String[] args) {
    VideoDownloader videoService = new CachedVideoDownloader();

    System.out.println("--- First Request ---");
    // This triggers the RealSubject download
    videoService.downloadVideo("https://video.com/proxy-pattern");

    System.out.println("\n--- Second Request (Same URL) ---");
    // This is handled entirely by the Proxy; no network call occurs
    videoService.downloadVideo("https://video.com/proxy-pattern");
  }
}
