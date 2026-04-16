# Design Ecommerce Product Listing (Video Processing)

## Overview

This system design covers the architecture for building an ecommerce product listing with a heavy focus on video processing. It includes handling rich media like uploading raw videos and converting them into multiple qualities (e.g., 360p, 720p, 1080p), generating timestamp-based thumbnail previews, and integrating HLS playlists for seamless playback.

## Key Components

- **Media Processing Workers:** Distributed workers handle heavy computational tasks like video transcoding.
- **Storage Strategy:** Object storage (like AWS S3) for raw videos, transcoded videos, and generated thumbnails.
- **Content Delivery Network (CDN):** Fast delivery for static assets like thumbnails, HLS playlists, and TS segments globally.
- **API Gateway:** Central entry point for clients, handling product data and streaming requests.

## Considerations

- **High Read Throughput:** Use caching (Redis/Memcached) heavily to serve product listings fast.
- **Search & Filtering:** System integration with search engines like Elasticsearch.
