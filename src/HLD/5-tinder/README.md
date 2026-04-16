# Designing Tinder

## Overview
Designing a dating application flow mimicking Tinder. The primary focus is the Feed generation and the swipe mechanism (left/right) which drives user engagement. 

## Key Requirements
- **Feed Criteria and Preferences:** Match age range, geolocation radius, search criteria.
- **Great UX:** Users should never be shown a profile they have previously swiped on.
- **Low Latency:** Loading the next profile should be immediate (prefetching).

## Key Components
- **Geospatial Database:** Services like Redis Geospatial, PostGIS, or custom QuadTree/Geohash to quickly find users within a radius.
- **Recommendation Engine:** Determines user relevancy scores based on activity, desirability (ELO score), and shared interests.
- **Swipe Storage:** Storing enormous volumes of bidirectional graph events (who liked whom) using distributed NoSQL (like Cassandra or DynamoDB).
- **Match Notification Workers:** Identifies mutual right-swipes and kicks off real-time notifications via WebSockets.
