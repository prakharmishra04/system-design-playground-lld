# Self-Service Infrastructure Management Platform
## High-Level Design (HLD) Problem Statement

---

## 1. Overview

Design a **self-service infrastructure management platform** for internal engineering teams that enables users to provision and manage infrastructure resources through a **dynamic, form-driven user interface**.

The platform acts as a centralized **Control Plane** responsible for validating user intent, managing request lifecycle and state, enforcing access control, orchestrating infrastructure workflows, and delegating actual resource provisioning to an execution layer.

The system must be **extensible by design**, support **full lifecycle management**, and be capable of adding new cloud providers and resource types with minimal changes.

---

## 2. Users and Access Control

### Users
- Internal engineering team members of the organization
- Authenticated using **SSO**
- Each user has a unique internal identity

### Roles
- **Requester**: Can create and manage infrastructure requests
- **Approver**: Can approve infrastructure requests (future extension)
- **Admin**: Can manage infrastructure catalog, policies, and configuration

### Authorization
- Role-based access control (RBAC)
- Permissions determine which infrastructure types a user can create
- Initial scope:
    - Organization-wide access
    - No team or sub-organization isolation
- Architecture must support future extension to:
    - Team-based access control
    - Sub-organization policies
    - Custom role definitions

---

## 3. Infrastructure Scope

### Cloud Providers
- **AWS only (initial)**
- Design must be extensible to support additional providers (GCP, Azure)

### Resource Scope
- **Databases only (initial)**
    - Example: RDS MySQL, RDS PostgreSQL
    - Deployment models:
        - Fully managed
        - Self-managed
- Architecture must support adding new resource types such as compute, cache, queues, and storage

### Lifecycle Management
- Create
- Update / Resize
- Pause / Resume (if supported)
- Delete

---

## 4. Self-Service UI and Dynamic Forms

### Infrastructure Selection Flow
Cloud Provider → Resource Type → Service → Deployment Model → Configuration

### Form Behavior
- Forms are dynamically generated
- Dropdown options update based on prior selections and provider capabilities
- Form definitions are backend-driven
- All input validation is enforced at the backend

### Example Flow
- Provider: AWS
- Resource Type: Database
- Service: RDS
- Deployment Model: Fully Managed
- UI dynamically loads required configuration fields and allowed values

---

## 5. Control Plane Responsibilities

The Control Plane is responsible for:

- Authenticating and authorizing users
- Serving infrastructure catalog and form schemas
- Accepting infrastructure requests
- Validating request payloads
- Persisting request and resource state
- Orchestrating provisioning workflows
- Tracking execution progress
- Exposing request status and provisioned resource metadata

---

## 6. Request and Orchestration Model

### Request Characteristics
- Requests are asynchronous and long-running
- Each request has a unique identifier
- Requests transition through well-defined states

### Example Request States
DRAFT → SUBMITTED → IN_PROGRESS → SUCCESS | FAILED

### Orchestration
- Control Plane manages execution workflows
- Supports retries and failure handling
- Workflow design must support future approval steps

---

## 7. Execution Layer

- Infrastructure provisioning is performed using **Terraform**
- Control Plane does not directly invoke cloud provider APIs
- Execution layer responsibilities:
    - Translate requests into Terraform plans
    - Apply infrastructure changes
    - Report execution status back to the Control Plane
- Terraform enables provider abstraction and future multi-cloud support

---

## 8. Observability and Auditability

- Users can view:
    - Request status
    - Provisioned resource details
- System maintains:
    - Audit logs for all infrastructure actions
    - User and timestamp metadata
- Failures return meaningful error information

---

## 9. Non-Functional Requirements

| Category        | Requirement |
|-----------------|-------------|
| Scalability     | Support thousands of infrastructure requests |
| Availability    | Control Plane must be highly available |
| Consistency     | Eventual consistency acceptable for UI |
| Security        | Secrets must never be stored in plaintext |
| Reliability     | Retryable and resumable workflows |
| Idempotency     | Duplicate requests must not create duplicate resources |
| Extensibility   | New providers and resource types via plugins or configuration |

---

## 10. Out of Scope

- Cost optimization and billing
- Auto-scaling policies
- Advanced Day-2 operations (patching, tuning)
- Cross-account or multi-account cloud setups (initially)

---

## 11. Success Criteria

The system should allow an engineering team member to:

1. Select an infrastructure service through a guided UI
2. Submit a validated infrastructure request
3. Track the request state throughout its lifecycle
4. Receive details of the provisioned resource
5. Manage the resource through supported lifecycle operations

---
