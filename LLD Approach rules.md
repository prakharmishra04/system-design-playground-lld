# 🧠 Universal LLD Problem-Solving Rulebook

This document defines a **repeatable, phase-based mental model** for approaching
any Low-Level Design (LLD) problem in interviews or real systems.

The goal is to:
- Avoid freezing
- Avoid over-engineering
- Produce clean, extensible, senior-level designs
- Reason clearly under ambiguity

---

## Core Philosophy

> **Design behavior first, structure later.**

- Flows describe **what happens**
- Responsibilities describe **who does what**
- Interfaces describe **what can change**
- Classes are **implementation details**

---

# PHASE 0 — Problem Framing (1–2 minutes)

### Objective
Align on **what is being designed**, not how.

### Rules
- Restate the problem in your own words
- Identify:
    - Who interacts with the system
    - What commands the system supports
    - What success looks like

### Output
- A short verbal or written summary
- No classes, no UML, no code

---

# PHASE 1 — Identify SYSTEM FLOWS (Most Important Phase)

### Objective
Describe **behavioral flows**, not object lifetimes.

### Rules
1. A flow is triggered by a **command** (user/system action)
2. Flows are written as **verbs**, not nouns
3. Ignore constructors, wiring, and setup details
4. Write flows in plain text or comments

### Key Rule (Creation in Flow)
> Include creation **only if creation itself is the purpose of the request**

- ✅ Include:
    - CreateGame
    - CreateOrder
    - CreateAccount
- ❌ Exclude:
    - new Board()
    - new Cell()
    - new DTO()

### Example Flow Format
Command →
validate →
core action →
rule evaluation →
state update →
response


### Output
- 3–6 core flows
- Each flow fits in 5–8 steps

---

# PHASE 2 — Extract RESPONSIBILITIES from Flows

### Objective
Identify **units of responsibility** that can change independently.

### Rules
1. Do NOT think in classes yet
2. Ask repeatedly:
   > "What is one thing that must be done well?"
3. Responsibilities are:
    - Verbs
    - Reasons for change
4. Avoid generic buckets (Manager, Helper, Utils)

### Examples
- Turn coordination
- Validation
- Rule evaluation
- State persistence
- Transition resolution

### Output
- A list of responsibilities
- Still no classes

---

# PHASE 3 — Define AGGREGATE ROOT & CONSISTENCY BOUNDARY

### Objective
Decide **what owns correctness and concurrency**.

### Rules
1. There must be **one primary consistency boundary**
2. All state mutations flow through it
3. This boundary defines:
    - Thread safety
    - Transaction scope
    - Future sharding

### Typical Answer
- One aggregate root per core entity (e.g., Game, Order, Ride)

### Output
- One sentence:
  > "`X` is my aggregate root and concurrency boundary."

---

# PHASE 4 — Identify VARIATION POINTS (Interfaces)

### Objective
Protect the system from future changes.

### Rules
1. Ask:
   > "What is likely to change?"
2. Create interfaces ONLY for:
    - Business variation
    - Configurable behavior
3. Do NOT abstract:
    - Data holders
    - Pure domain entities
4. One future change is enough — don’t predict everything

### Common Variation Points
- Rules
- Strategies
- Policies
- Algorithms
- External integrations

### Output
- A list of interfaces with clear intent

---

# PHASE 5 — Map RESPONSIBILITIES → INTERFACES → CLASSES

### Objective
Convert abstract responsibilities into concrete structure.

### Rules
1. Interfaces first, implementations later
2. Prefer composition over inheritance
3. Avoid god objects
4. Class names reflect **responsibility**, not patterns

### Sanity Check
- Each class has:
    - One clear responsibility
    - One reason to change

### Output
- High-level class list
- Interface vs implementation clarity

---

# PHASE 6 — Apply PATTERNS NATURALLY (Not Forced)

### Objective
Use patterns only where they reduce complexity.

### Rules
- Patterns emerge from variation points
- Never introduce a pattern “because interview”
- Be able to justify each pattern in one sentence

### Common Natural Mappings
| Need | Pattern |
|----|--------|
| Pluggable behavior | Strategy |
| Object creation | Factory |
| Lifecycle handling | State |
| Action encapsulation | Command |
| Rule pipelines | Chain of Responsibility |
| Notifications | Observer |

### Output
- Pattern usage with justification

---

# PHASE 7 — THREAD SAFETY & CONCURRENCY STRATEGY

### Objective
Ensure correctness under concurrency.

### Rules
1. Lock at the **aggregate root**
2. One mutation at a time per aggregate
3. Avoid global locks
4. Prefer immutability elsewhere

### Must Answer Clearly
- What is synchronized?
- At what granularity?
- Why is this sufficient?

### Output
- Clear concurrency story (even without code)

---

# PHASE 8 — EXTENSION SAFETY CHECK

### Objective
Prove Open/Closed Principle in practice.

### Rules
For each future feature, ask:
> "Can I add this without modifying core engine code?"

If not:
- Revisit interfaces
- Revisit responsibility boundaries

### Output
- Confidence in extensibility
- No rewrites required

---

# PHASE 9 — IMPLEMENT LAST (If Required)

### Objective
Translate design into clean, testable code.

### Rules
1. Start with interfaces
2. Keep methods small
3. Avoid premature optimization
4. Write code that explains itself

---

# FINAL INTERVIEW MANTRA

> **Flow → Responsibility → Interface → Class → Code**

If you follow these phases **in order**, you will:
- Never freeze in LLD interviews
- Avoid over-design
- Signal senior/staff-level thinking
- Produce systems that evolve cleanly

---

# How to Practice

For every LLD problem:
1. Write flows
2. List responsibilities
3. Identify aggregate root
4. Mark variation points
5. Define interfaces
6. Implement minimally
7. Refactor once
8. Explain tradeoffs

Repeat until automatic.

---