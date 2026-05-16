# Distributed Rate Limiting Service

## Overview

A distributed rate limiting system being built using Spring Boot and Redis to enforce API request quotas across scalable backend services.

Current implementation includes:
- Spring Boot REST APIs
- Fixed Window rate limiting algorithm
- Configurable in-memory policies
- Per-client + per-API request throttling

---

## Architecture Diagram

![Architecture Diagram](docs/distributedRateLimiting_v1.png)

Editable version:
[Open in Excalidraw](https://excalidraw.com/#json=euAq5qziIBuD7gbBwefY8,m70cVKbQbdPVi85_vI7yHA)

---

## Current Features

- Fixed Window rate limiting
- Configurable API policies
- Request quota enforcement
- HTTP 429 response for rejected requests
- Structured request/response DTOs
- Layered Spring Boot architecture

---

## Current Architecture Flow

Client Request
    ↓
Spring Boot Controller
    ↓
RateLimitService
    ↓
RedisRateLimitService
    ↓
Redis (shared state)

---

## Known Limitations

Current implementation performs multiple Redis operations separately.

Potential issues:

- non-atomic request handling
- race conditions under concurrency
- multiple network round trips

Future improvement:

Move throttling logic into Redis Lua scripts for atomic execution.

---

## Project Status

v0.2 Redis Fixed Window Completed

---

## Example API

### Request

```http
POST /api/v1/rate-limit/check