# Production-Grade Distributed Rate Limiting Service — Build Blueprint

## Important Context

This project is being built **while learning Spring Boot, Redis, Docker, observability, and distributed backend patterns**. That is completely fine.

Goal is **not** to pretend to be an expert on day 1.
Goal is:

> learn by building one serious engineering system end-to-end.

So this document will act as:

* learning roadmap
* implementation checklist
* architecture reference
* resume extraction source later

We will build this incrementally, not all at once.

---

## Mindset Rule for This Project

Do not learn Spring Boot separately for 2 months.
Do not learn Redis separately for 1 month.
Do not learn Docker separately.

Instead:

> learn exactly what this project forces you to learn.

That is the fastest industry-style learning.

We are not building a tutorial.
We are building in layers.

---

## Final End Goal of Project

A distributed backend service that decides whether incoming requests should be ALLOWED or REJECTED based on configured request quotas, while multiple application nodes share a centralized Redis state and make atomic throttling decisions.

Includes:

* multiple rate limiting algorithms
* Redis atomic Lua scripts
* Dockerized multi-node deployment
* Nginx load balancing
* k6 benchmark suite
* Prometheus + Grafana observability

---

## Project Build Strategy (Very Important)

We will build this in 7 phases.
Only focus on one phase at a time.

### Phase 1 — Learn enough Spring Boot to expose REST APIs

Need only:

* Controller
* Service
* DTO
* application.yml
* basic Maven setup

### Phase 2 — Single node in-memory rate limiter logic

Implement algorithm locally first.
No Redis yet.
Understand the business logic.

### Phase 3 — Move state to Redis

Learn:

* Redis key/value
* TTL
* counters
* sorted sets

### Phase 4 — Atomic Lua scripts

Learn why race conditions happen.
Then move decision logic into Redis Lua.

### Phase 5 — Multi-instance distributed deployment with Docker + Nginx

Run 3 app nodes against one Redis.

### Phase 6 — Benchmark using k6

Generate traffic and collect latency/rejection numbers.

### Phase 7 — Prometheus + Grafana metrics

Observe the service like production infra.

---

## Current Rule

Since learning is happening in parallel, we will never jump to advanced phase before foundation is ready.

Every phase will contain:

* what to learn
* what code to write
* why it matters
* exact implementation steps

---

## Next Immediate Step

Design the technical skeleton:

* folder structure
* APIs
* classes
* first algorithm to build

---

# Complete 4-Week Execution Roadmap

This roadmap exists so that if there is a gap of a few days, you can come back and immediately understand:

* current phase
* current learning goal
* current implementation target
* what comes next

The goal is not to become an expert in every technology before starting.
The goal is:

> learn by building the system incrementally.

---

# WEEK 1 — FOUNDATION (Spring Boot + In-Memory Rate Limiter)

## Goal

Build a working single-node rate limiter using Spring Boot and Java memory.

This week is mainly about:

* understanding backend structure
* learning Spring Boot basics
* understanding API request flow
* implementing first rate limiting algorithm

---

## Concepts To Learn This Week

Only focus on these:

### Spring Boot Basics

* @RestController
* @RequestBody
* @PostMapping
* @Service
* DTO
* request/response flow

### Java Basics Needed

* Map
* HashMap
* storing counters
* timestamps
* basic conditional logic

### Backend Concepts

* API contracts
* separation of concerns
* service layer
* request lifecycle

Do NOT deep dive into Redis, Docker, Kafka, Prometheus, or Lua this week.

---

## Day-by-Day Plan

### Day 1

Completed:

* Spring Boot project bootstrap
* actuator health endpoint
* initial API setup

### Day 2

Completed:

* first controller
* dummy rate limit API

### Day 3

Completed:

* DTO package
* request DTO
* response DTO
* structured JSON request/response

### Day 4

Completed:

* service layer
* controller delegates logic to service
* clean backend structure

### Day 5

Target:
Implement Fixed Window algorithm using Java HashMap.

Learn:

* request counting
* time window logic
* how rate limiting works internally

Expected behavior:

* first 10 requests allowed
* 11th request rejected
* reset after 60 seconds

### Day 6

Target:
Add simple configurable policy structure.

Examples:

* payment API = 10 req/min
* login API = 5 req/min

Policy can stay in memory for now.

### Day 7

Target:

* Postman testing
* README update
* GitHub commit cleanup
* milestone release

Milestone Tag:
v0.1-in-memory-fixed-window

---

# WEEK 1 SUCCESS CRITERIA

By end of Week 1:

✔ Spring Boot fundamentals understood
✔ request → controller → service → response flow understood
✔ first working rate limiter built
✔ Fixed Window algorithm working locally
✔ GitHub repository structured properly

---

# WEEK 2 — REDIS INTEGRATION

## Goal

Move request counters from local memory into Redis.

This is the first real distributed systems step.

---

## Concepts To Learn

### Redis Basics

* key/value
* TTL
* INCR
* EXPIRE
* distributed shared state

### Spring Boot Redis Integration

* Redis configuration
* RedisTemplate basics

---

## Week 2 Plan

### Day 1

Learn Redis fundamentals.

### Day 2

Add Redis configuration in application.yml.

### Day 3

Connect Spring Boot to Redis.

### Day 4

Implement Fixed Window using Redis.

### Day 5

Test Redis counters manually.

### Day 6

Add basic policy API.

### Day 7

README update + milestone commit.

Milestone Tag:
v0.2-redis-fixed-window

---

# WEEK 2 SUCCESS CRITERIA

✔ Spring Boot connected to Redis
✔ request counters stored in Redis
✔ rate limiting works even after app restart
✔ TTL-based expiration working

---

# WEEK 3 — CONCURRENCY + DISTRIBUTED SETUP

## Goal

Make the system correct under concurrent traffic and multi-node deployment.

This is where project becomes true distributed systems engineering.

---

## Concepts To Learn

### Concurrency Concepts

* race conditions
* atomicity
* shared state consistency

### Redis Lua

* atomic execution
* server-side logic

### Distributed Deployment

* Docker basics
* docker-compose
* Nginx load balancing

---

## Week 3 Plan

### Day 1

Understand race conditions.

### Day 2

Write Redis Lua script.

### Day 3

Integrate Lua script into Spring Boot.

### Day 4

Dockerize Spring Boot application.

### Day 5

Create multi-node docker-compose setup.

### Day 6

Add Nginx load balancer.

### Day 7

Test distributed request flow.

Milestone Tag:
v0.3-distributed-limiter

---

# WEEK 3 SUCCESS CRITERIA

✔ multiple limiter nodes running
✔ shared Redis state working
✔ Lua atomic decisions working
✔ load balancer distributing traffic
✔ consistent allow/reject decisions

---

# WEEK 4 — BENCHMARKING + OBSERVABILITY + FINAL POLISH

## Goal

Convert project into production-grade engineering portfolio project.

---

## Concepts To Learn

### Benchmarking

* throughput
* p95 latency
* burst traffic
* hot key behavior

### Observability

* metrics
* Prometheus
* Grafana dashboards

---

## Week 4 Plan

### Day 1

Add k6 benchmark script.

### Day 2

Run benchmark tests.

### Day 3

Add Spring Boot metrics.

### Day 4

Setup Prometheus.

### Day 5

Setup Grafana dashboards.

### Day 6

Document benchmark observations.

### Day 7

Final README cleanup + resume bullets.

Milestone Tag:
v1.0-production-grade-rate-limiter

---

# WEEK 4 SUCCESS CRITERIA

✔ benchmark suite available
✔ performance metrics captured
✔ Grafana dashboards working
✔ production-quality README completed
✔ project ready for resume/GitHub showcase

---

# FINAL PROJECT OUTCOME

By the end of this roadmap, the project should include:

✔ Spring Boot REST APIs
✔ Fixed Window rate limiting
✔ Redis distributed state
✔ Redis Lua atomic logic
✔ Multi-node deployment
✔ Nginx load balancing
✔ Docker Compose setup
✔ k6 benchmarking
✔ Prometheus metrics
✔ Grafana dashboards
✔ Professional README documentation

---

# IMPORTANT LEARNING PRINCIPLE

Do NOT attempt to master every technology before coding.

Correct learning loop:

need → learn → implement → test → understand

This project itself is the learning platform.
