# Rate Limiter Service – Initial LLD Practice Problem Statement

## Purpose of This Exercise

This document defines a **focused Low-Level Design (LLD) problem statement** for building a **rate limiter engine** in Java.

The goal of this phase is to:

* Start **LLD-first**, before HLD
* Design **clean class-level abstractions**
* Draw **Class UML diagrams** before writing code
* Produce **machine-code-ready Java design**

This version intentionally **limits scope** so you can concentrate on:

* Object modeling
* Design patterns
* Thread safety
* Algorithm encapsulation

This LLD will later act as the **foundation** for a distributed HLD extension.

---

## 1. Problem Statement

Design and implement a **Rate Limiter Engine** that determines whether incoming requests should be allowed or rejected based on preconfigured rate-limiting rules.

The system:

* Runs inside a **single JVM**
* Is **thread-safe** and supports concurrent requests
* Loads all configurations **once at startup**
* Enforces rate limits per `(clientId, endpoint)` pair

The engine must return a **structured decision object** describing the outcome of each request.

---

## 2. Inputs & Outputs

### Input

Each request to the rate limiter consists of:

```text
clientId: String
endpoint: String
```

### Output

Each rate-limit decision must return:

```text
allowed: boolean
remaining: int
retryAfterMs: long | null
```

Where:

* `allowed` indicates whether the request can proceed
* `remaining` indicates remaining requests/tokens in the current window or bucket
* `retryAfterMs` indicates how long the client should wait before retrying (null if allowed)

---

## 3. Functional Requirements (LLD Scope)

### Core Behavior

1. The system must expose a **single entry point** for rate-limiting checks.
2. The system receives requests identified by `(clientId, endpoint)`.
3. For each request, the system must:

    1. Resolve the rate-limit configuration for the endpoint
    2. Apply the configured rate-limiting algorithm
    3. Produce a structured result

### Configuration

4. Rate-limit configurations are:

    * Loaded **once at startup**
    * Immutable at runtime
5. Each endpoint configuration specifies:

    * The **rate-limiting algorithm** to use (e.g., `TokenBucket`, `SlidingWindowLog`)
    * Algorithm-specific parameters

Example:

```yaml
/login:
  algorithm: TokenBucket
  capacity: 5
  refillRatePerSecond: 1

/search:
  algorithm: SlidingWindowLog
  windowSizeMs: 60000
  maxRequests: 100
```

6. If an endpoint has **no explicit configuration**, a **default rate limit** must be applied.

---

## 4. Supported Algorithms (Initial Set)

The design must support (at minimum):

* Token Bucket
* Sliding Window Log

The system must be designed so that **new algorithms can be added without modifying existing algorithm code**.

---

## 5. Non-Functional Requirements

### Thread Safety & Concurrency

1. The system must be **thread-safe** within a single JVM.
2. Multiple concurrent requests for the same `(clientId, endpoint)` must not violate rate limits.
3. High contention scenarios must not corrupt internal state.

### Performance

4. Each rate-limit check should run in **O(1)** or amortized O(1) time.
5. Lock contention should be minimized.

### Design Quality

6. Code must follow SOLID principles.
7. Clear separation of responsibilities is required.
8. Business logic must be testable in isolation.

---

## 6. Explicit Out-of-Scope Items (For This Phase)

The following are **intentionally excluded** from this LLD exercise:

* Distributed rate limiting (Redis, DynamoDB, coordination)
* Dynamic configuration updates
* Metrics, logging, and monitoring
* Advanced configuration validation
* Persistence of counters across restarts

---

## 7. Expected Design Outcomes

By completing this exercise, you should be able to produce:

1. A **Class UML Diagram** showing:

    * Core interfaces
    * Concrete algorithm implementations
    * Thread-safety boundaries
2. A **clean Java package structure**
3. Clearly defined extension points for:

    * New algorithms
    * Future distributed storage

---

## 8. Design Expectations (Strong Hints)

While implementation is open-ended, a strong design is expected to include:

* A central `RateLimiter` interface
* An `Algorithm` abstraction (Strategy Pattern)
* A factory for algorithm instantiation
* Algorithm-specific state isolation per `(clientId, endpoint)`
* Immutable configuration objects

Thread safety should be achieved using:

* Concurrent collections
* Atomic primitives
* Minimal synchronized sections

---

## 9. Evaluation Criteria

This exercise evaluates:

* Quality of class decomposition
* Correctness under concurrency
* Ease of extensibility
* Clarity of UML diagrams
* Readiness for future HLD expansion

---

## 10. Next Phases (Not Implemented Now)

After completing this LLD phase, the design should naturally extend to:

* External stores (Redis)
* Distributed consistency models
* Runtime configuration updates
* Metrics and observability

This document should be treated as the **baseline LLD specification** on top of which production-grade extensions can be built.
