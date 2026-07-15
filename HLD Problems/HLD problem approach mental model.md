# CLEAR-HLD Framework
## A Mental Model for High-Level System Design Interviews

---

## Step 0: Frame the Problem (Scope First)

**Goal:** Demonstrate seniority by controlling scope before designing.

**What to do:**
- Clarify the problem boundaries
- State assumptions explicitly
- Define what is in scope vs out of scope

**What to say in an interview:**
> “This is a broad problem. To keep it tractable, I’ll scope the first version and design it to be extensible.”

---

## Step 1: C — Core Use Cases

**Goal:** Identify the minimum set of flows the system must support.

**Guidelines:**
- Limit to 3–5 core user journeys
- Focus on business-critical paths
- Defer edge cases unless asked

**Examples:**
- Create a request
- Process request asynchronously
- Track request state
- Manage lifecycle of an entity

---

## Step 2: L — Logical Components

**Goal:** Decompose the system by responsibility.

**Principles:**
- One component = one primary responsibility
- Avoid premature microservice boundaries

**Typical Components:**
- API / Gateway
- Authentication & Authorization
- Core Domain Service
- State Management / Persistence
- Async Processing / Workflow Engine
- External Integrations

---

## Step 3: E — Entities & State

**Goal:** Identify what needs to be persisted and tracked over time.

**Key Questions:**
- What has a stable identity?
- What changes state?
- What needs auditing?

**Common Entities:**
- User
- Request
- Resource
- Policy
- Workflow State

**Focus Area:**
- State transitions
- Idempotency
- Auditability

---

## Step 4: A — APIs & Contracts

**Goal:** Define how components communicate.

**Guidelines:**
- Design contracts, not endpoints
- Separate commands from queries
- Decide sync vs async interactions

**Considerations:**
- Idempotent APIs
- Event-driven updates
- Callback or polling mechanisms

---

## Step 5: R — Reliability & Non-Functional Requirements

**Goal:** Address system qualities expected at senior level.

**Must Cover:**
- Scalability
- Availability
- Consistency
- Security
- Observability

**Interview Tip:**
- Always explain tradeoffs explicitly

---

## Step 6: H — Hooks for Evolution

**Goal:** Future-proof the design without over-engineering.

**Questions to Ask:**
- What is likely to change?
- Where should extension points exist?

**Common Patterns:**
- Metadata-driven configuration
- Plugin-based architecture
- Versioned schemas
- Strategy-based execution

---

## Suggested Interview Time Allocation

| Time (min) | Focus Area |
|------------|------------|
| 0–5 | Clarification & scope |
| 5–15 | Core use cases & components |
| 15–30 | Deep dive on critical flows |
| 30–45 | Data, state & failure handling |
| 45–55 | Non-functional requirements |
| 55–60 | Extensibility & summary |

---

### Universal Whiteboard Template (Logical Architecture)

- **Client / UI**
    - Initiates user interactions and requests
- **API / Gateway**
    - Authentication and authorization
    - Request routing and validation
- **Core Service**
    - Implements business logic
    - Coordinates workflows
    - Manages state transitions
    - Interacts with persistence and async systems
- **State Management**
    - Persists system and request state
    - Enables auditability and recovery
- **Workflow / Async Processing**
    - Handles long-running and asynchronous tasks
    - Manages retries and failure handling
- **External Systems**
    - Cloud providers
    - Third-party services
    - Execution engines (e.g., Terraform)



---

## Anti-Patterns to Avoid

- Starting with database schema
- Designing for every future requirement
- Over-indexing on cloud or framework specifics
- Getting stuck on UI details
- Skipping assumptions and tradeoffs

---

## One-Line Mental Anchor

> “Scope first, design core flows, persist state explicitly, handle failure, and leave clean hooks for change.”


