# 🔔 Multi-Tenant Workflow & Notification Platform – System Design Problem

## Objective

Design and implement a **multi-tenant workflow and notification platform** that allows different customer organizations (tenants) to define **custom business workflows** triggered by events and executed via configurable actions.

The system must:
- Support **multiple tenants with strict isolation**
- Allow **tenant-defined workflows** without code changes
- Execute workflows reliably at scale
- Be designed first as a **local, in-memory system**
- Be architected for evolution into a **distributed SaaS platform**

This project is intended for **system design interview preparation** with strong emphasis on **LLD, extensibility, and platform design**.

---

## 1. Functional Requirements (FR)

### Multi-Tenancy
1. The system shall support **multiple tenants (organizations)**.
2. Each tenant must have **complete isolation** of:
    - Data
    - Workflows
    - Executions
3. A workflow defined by one tenant must never affect another tenant.

---

### Events
4. The system shall support **events** such as:
    - Entity created
    - Entity updated
    - Status changed
    - Custom domain events
5. Events must be:
    - Typed
    - Versioned
    - Immutable once published

---

### Workflows
6. A tenant can define **multiple workflows**.
7. Each workflow consists of:
    - Trigger conditions
    - One or more steps
    - Optional branching logic
8. Workflow execution must be **deterministic**.
9. Workflows can be:
    - Enabled
    - Disabled
    - Updated (new version)

---

### Conditions
10. A workflow can specify conditions such as:
    - Field comparisons
    - Threshold checks
    - Event metadata checks
11. Conditions must be composable using:
    - AND
    - OR
    - NOT

---

### Actions
12. A workflow step can execute one or more actions:
    - Send notification (email, SMS, push)
    - Invoke webhook
    - Update entity
    - Publish new event
13. Actions must be **pluggable**.
14. Adding a new action type must **not require modifying existing engine code**.

---

### Notifications
15. Notification channels supported:
    - Email
    - SMS
    - Push
    - Webhook
16. Tenants can configure:
    - Notification templates
    - Delivery preferences
17. Delivery failures must be handled gracefully.

---

### Execution & Tracking
18. Each workflow execution must have:
    - Unique execution ID
    - Execution status
    - Step-level audit logs
19. Failed steps may be:
    - Retried
    - Compensated
    - Skipped (based on configuration)

---

## 2. Non-Functional Requirements (NFR)

### Design & Extensibility
1. Follow **SOLID principles**.
2. Favor **composition over inheritance**.
3. Separate:
    - Workflow definition
    - Execution engine
    - Action execution
4. Core engine must not change when:
    - New actions are added
    - New conditions are added

---

### Scalability & Performance
5. The system must support:
    - Thousands of tenants
    - Millions of events per day
6. Workflow execution must be asynchronous.
7. Event processing must be horizontally scalable.

---

### Concurrency & Thread Safety
8. Workflow execution must be thread-safe.
9. Multiple workflows may execute concurrently for the same tenant.
10. The same workflow execution must not be processed twice.

---

### Reliability
11. Workflow execution must be idempotent.
12. System must support retries and partial failures.
13. Execution state must be recoverable after crashes.

---

### Governance & Limits
14. Per-tenant limits must exist for:
    - Number of workflows
    - Events per minute
    - Actions per execution
15. Limit violations must be enforced consistently.

---

## 3. Constraints

- No UI required (API / test-driven interaction is sufficient).
- Language: **Java**
- No external workflow engines.
- Initial implementation may be in-memory, but must be persistence-ready.

---

## 4. Core Domain Objects (Must Exist)

Your Low-Level Design must explicitly model:

- `Tenant`
- `Event`
- `Workflow`
- `WorkflowVersion`
- `Condition`
- `Action`
- `WorkflowExecution`
- `ExecutionStep`
- `Notification`
- `ExecutionResult`

Each object must have a **single, clear responsibility**.

---

## 5. Required Design Patterns

Your design is expected to naturally use:

| Concern | Design Pattern |
|------|----------------|
| Workflow creation | Factory |
| Condition evaluation | Specification |
| Action execution | Command |
| Workflow execution flow | Template Method |
| Event handling | Observer |
| Extensibility | Strategy |
| Execution state | State |

Pattern usage must be **intentional and explainable**.

---

## 6. Low-Level Design (LLD) Expectations

Your LLD should clearly define:
1. Interfaces vs implementations
2. Extension points
3. Thread-safety boundaries
4. Execution lifecycle
5. Failure handling strategy

You should be able to derive:
- Class diagrams
- Sequence diagrams for workflow execution
- State diagrams for execution lifecycle

---

## 7. High-Level Design (HLD) – Future Evolution

The design must naturally evolve into:

- Event ingestion service
- Workflow execution service
- Notification service
- Tenant configuration service
- Persistence & audit store

---

### Distributed System Challenges to Address
- Event ordering
- Idempotency
- Sharding by tenant
- Backpressure handling
- Retry semantics
- Fault isolation

---

## 8. Extension Scenarios (Must Not Require Rewrites)

The design must support:
1. Delayed workflows
2. Scheduled workflows
3. Approval-based workflows
4. Conditional branching
5. Human-in-the-loop actions

---

## Goal

This system should act as:
- A **reference platform design**
- A **Salesforce-style system design problem**
- A reusable foundation for future automation systems

Focus on **correct abstractions over implementation shortcuts**.
