# Designing PasteBin and Github Gists

## Overview
A system design for a text storage service like PasteBin or GitHub Gists. The primary goal is to allow users to store plain text or code snippets and generate a unique URL to share with others. 

## Key Requirements
- **High Read-to-Write Ratio:** Snippets are shared and read far more often than they are created.
- **Short URL Generation:** System needs to generate unique, easily shareable short URLs for each text snippet.
- **Data Expiration:** Option for snippets to expire automatically after a certain time (e.g., 24 hours, 1 week, or never).

## System Components
- **API Gateway:** Routes read and write requests.
- **Key Generation Service (KGS):** Similar to a URL Shortener, a service that pre-generates unique base62 IDs for incoming text.
- **Storage Layer (Object Storage / NoSQL):** Snippet metadata (author, timestamp, expiration) stored in NoSQL (like DynamoDB/Cassandra) or a relational DB. Large text contents are often best placed in Object Storage (S3) or specialized document stores.
- **Caching Layer (Redis/Memcached):** Heavily caches the most accessed text snippets to ensure fast retrieval.
- **Cleanup Worker:** A background worker or cron job that periodically purges expired text snippets.
