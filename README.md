# Distributed Rate Limiting Service

## Overview
A production-grade distributed rate limiting system built using Spring Boot and Redis to enforce API request quotas across horizontally scaled services.

---

## Architecture Diagram

![Architecture Diagram](./docs/DistributedRateLimiting_v1.png)

Or view editable diagram:
[Open in Excalidraw](https://excalidraw.com/#json=euAq5qziIBuD7gbBwefY8,m70cVKbQbdPVi85_vI7yHA)

---

## Key Concepts
- Stateless limiter nodes
- Centralized Redis state
- Atomic rate limiting via Lua
- Horizontal scalability

---

## Tech Stack
- Java / Spring Boot
- Redis
- Docker
- Prometheus + Grafana
- k6

---

## Features (Planned)
- Fixed Window / Sliding Window / Token Bucket
- Distributed enforcement
- Benchmarking suite
- Observability dashboard

---

## Project Status
  In Progress
