# Design a Rate Limiter Service (HLD + LLD Practice Problem)

## Purpose of This Document

This problem statement is intended to be used as a **reference design exercise** for practicing:

* High-Level Design (HLD)
* Low-Level Design (LLD)
* UML modeling
* Java extensible architecture
* Concurrency-safe and distributed system design

The final outcome should be:

* A clean **Java implementation** runnable locally
* An architecture that **naturally scales** to a distributed setup
* A reusable **system design template** for future problems

---

## 1. Problem Statement

Design a **Rate Limiter Service** that controls how frequently a client can access APIs.

The service must be:

* Usable as an **embedded Java library** (single JVM)
* Deployable as a **distributed service** (multiple instances)
* Extensible to support multiple rate-limiting algorithms
* Thread-safe and concurrency-safe
* Configurable without redeployment

The rate limiter should decide **whether to allow or reject a request** in real time based on configured limits.

---

## 2. Functional Requirements (FR)

### Core Behavior

1. The system must determine whether an incoming request should be **allowed or rejected**.
2. Rate limits must be enforceable on different dimensions:

    * User ID
    * API key
    * IP address
    * Endpoint
3. A rate limit is defined as:

    * `N requests per T time window`

### Algorithm Support

4. The system must support multiple rate-limiting algorithms:

    * Fixed Window Counter
    * Sliding Window Log / Counter
    * Token Bucket
    * Leaky Bucket
5. New algorithms must be addable **without modifying existing logic**.

### Configuration

6. Different APIs may have different limits.

    * Example:

        * `/login` → 5 requests / minute
        * `/search` → 100 requests / minute
7. Rate limit configurations must be changeable at runtime.
8. Burst traffic should be supported where applicable.

### Usage Modes

9. The system should work:

    * As a local in-memory rate limiter
    * As a distributed rate limiter using a shared store

---

## 3. Non-Functional Requirements (NFR)

### Thread Safety & Concurrency

1. The rate limiter must be **thread-safe within a single JVM**.
2. Concurrent requests for the same key must not violate configured limits.
3. The system must behave correctly under **high contention**.

### Performance

4. Each rate-limit decision must complete in **O(1)** time.
5. Added latency per request should be minimal (< few milliseconds).
6. Lock contention must be minimized.

### Scalability

7. The system must scale horizontally across multiple service instances.
8. Rate limits must remain correct when requests hit different nodes.

### Reliability & Fault Tolerance

9. The system must define behavior when the backing store is unavailable:

    * Fail-open or fail-closed
10. Temporary inconsistencies are acceptable if explicitly documented.

### Extensibility & Maintainability

11. Storage implementations must be swappable (In-memory, Redis, DB).
12. Core logic must follow SOLID principles.

### Observability

13. The system should expose:

* Metrics for allowed vs blocked requests
* Logging for rejected requests

---

## 4. Constraints & Assumptions

1. Clock skew may exist between servers.
2. Network partitions are possible.
3. Configuration updates may not propagate instantly.
4. High-cardinality keys (hot keys) may occur.

---

## 5. Expected High-Level Design (HLD)

### Key Components

* Request Interceptor / Filter
* RateLimiter Service
* Rate Limiting Strategy
* Rate Limit Store
* Configuration Provider
* Metrics & Logging

### Data Flow (Logical)

1. Request arrives
2. Key is resolved (user/IP/API)
3. Configuration is fetched
4. Algorithm is executed
5. Request is allowed or rejected

### Distributed Setup

* Shared backing store (e.g., Redis)
* Atomic operations for counters
* Eventual consistency accepted

---

## 6. Expected Low-Level Design (LLD)

### Core Abstractions

* RateLimiter
* RateLimitingStrategy
* RateLimitStore
* RateLimitKeyResolver
* RateLimitConfigProvider

### Design Patterns Expected

* Strategy Pattern (rate-limiting algorithms)
* Factory Pattern (algorithm creation)
* Bridge Pattern (storage abstraction)
* Observer Pattern (config updates)
* Singleton (rate limiter instance)
* Chain of Responsibility (request processing pipeline)

### Thread Safety Model

* JVM-level thread safety via:

    * Concurrent data structures
    * Atomic operations
* Distributed concurrency via:

    * Atomic store operations
    * Clearly defined consistency tradeoffs

---

## 7. Implementation Expectations

### Phase 1: Local Mode

* In-memory store
* Single JVM
* Concurrent request handling

### Phase 2: Distributed Mode

* External store integration (e.g., Redis)
* Multiple service instances
* Correctness under concurrency

---

## 8. Validation & Testing

* Unit tests for algorithms
* Concurrency tests (multi-threaded access)
* Load tests simulating burst traffic
* Failure simulations (store unavailable)

---

## 9. What This Exercise Is Evaluating

* Ability to translate requirements into clean abstractions
* Understanding of concurrency and distributed tradeoffs
* Practical use of design patterns
* Clean, extensible Java design
* Clear separation of HLD and LLD concerns

---

## 10. Outcome

At the end of this exercise, the system should:

* Be runnable locally
* Be scalable to distributed systems
* Serve as a reusable reference for future LLD and HLD problems

This document should be treated as the **baseline specification** for implementing and iterating on a production-grade Rate Limiter Service.
