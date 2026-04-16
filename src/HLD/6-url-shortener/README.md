# Designing URL Shortener

## Overview
Design of a service that accepts long, complex URLs and generates shortened, human-readable, pseudo-random URLs. The service typically processes around ~100M URLs per month. The main reasons for this are easier sharing in SMS/social media and aesthetics. 

## Shortening Logic
- **Base62 Encoding:** Generate unique alphanumeric combinations. (0-9, a-z, A-Z gives 62 characters). A 7-character Base62 string can hold over 3.5 trillion URLs.
- **Key Generation Service (KGS):** Pre-generates random unique keys in a massive database, caching them in memory for fast assignment to incoming long URLs.

## System Components
- **API Gateway / Load Balancer:** Distributes incoming read/write traffic.
- **Cache (Redis/Memcached):** Stores frequently accessed URL mappings to allow blazing fast redirect handling.
- **Database (NoSQL/RDBMS):** Stores the mappings persistently. Both a scalable datastore like Cassandra/DynamoDB or an RDBMS with sharding handle scale properly.
- **Analytics:** Tracking click-through rates, geolocations, and time-series access patterns.
