# Design a Rate Limiter

## Overview
This document covers the design of a rate limiter meant to prevent system breakdown under tremendous load. The goal is to limit the number of requests per user/IP/token in a given period while ensuring legitimate network traffic passes unhindered.

## Key Requirements
- **High Performance:** Minimal latency introduced to incoming API calls.
- **Accuracy:** The rate limiter should not falsely block legitimate requests.
- **Scalability:** Should handle traffic from distributed environments smoothly.
- **Resilience:** The limiter's crash should not take down the main service.

## Core Algorithms
- **Token Bucket / Leaky Bucket:** Ideal for general API-level limiting and bursting.
- **Fixed Window Counter:** Easy to implement, but vulnerable to spikes at the edges of the window.
- **Sliding Window Log / Counter:** More accurate and smooths out traffic over time boundaries.

## Architecture
- **API Gateway:** Intercepts traffic at the edge.
- **In-Memory Cache (Redis):** Stores counters and timestamps rapidly (e.g., using Redis caching logic or Redis sorted sets depending on the chosen algorithm).
