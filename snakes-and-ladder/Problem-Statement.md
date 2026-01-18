# 🎲 Snakes & Ladders – Extensible System Design Problem

## Objective

Design and implement an **extensible Snakes and Ladders game engine** in Java that:
- Works as a standalone local application
- Is cleanly designed using **OO principles and design patterns**
- Can later be evolved into a **distributed, online multiplayer gaming platform**
- Serves as a reference implementation for practicing **LLD → HLD system design**

This document is the **single source of truth** while implementing and evolving the system.

---

## 1. Functional Requirements (FR)

### Core Gameplay
1. The system shall support **N players** (2 ≤ N ≤ configurable limit).
2. Players take turns in a **strict round-robin order**.
3. The board consists of numbered cells from **1 to M** (default M = 100).
4. On a player’s turn:
    - The player rolls a dice.
    - The player moves forward by the dice value.
5. If a player lands on:
    - The **head of a snake**, they move down to the snake’s tail.
    - The **base of a ladder**, they move up to the ladder’s top.
6. A player must roll the **exact number** to reach the final cell.
7. The first player to reach the final cell **wins the game**.
8. After the game ends, **no further moves** are allowed.

---

### Configuration & Customization
9. Board size must be configurable.
10. Snake and ladder positions must be configurable.
11. Dice behavior must be pluggable:
    - Standard dice (1–6)
    - Custom dice (e.g., 1–8)
    - Deterministic dice (for testing)
12. Game rules must be extendable **without modifying core engine code**.

---

### Multiplayer & Game Lifecycle
13. The system must support **multiple games running concurrently**.
14. Each game must have a **unique game identifier**.
15. Players can:
    - Join a game before it starts
    - Leave a game before it starts
16. The system must maintain **independent game state per game**.

---

## 2. Non-Functional Requirements (NFR)

### Design Quality
1. Follow **SOLID principles**.
2. Favor **composition over inheritance**.
3. Separate concerns between:
    - Game engine
    - Board
    - Rules
    - Player actions
4. Code must be readable, testable, and extensible.

---

### Concurrency & Thread Safety
5. The system must be **thread-safe**.
6. Concurrent access to the same game must not corrupt state.
7. Only **one player move** can be processed at a time per game.
8. Multiple games must run independently without shared mutable state.

---

### Scalability & Reliability
9. Game state must be **serializable**.
10. The design must allow horizontal scaling in the future.
11. The system should be resilient to invalid actions.

---

## 3. Constraints

- No UI is required (CLI or test-driven interaction is sufficient).
- Implementation language: **Java**
- No external gaming frameworks.
- In-memory implementation first, but design must allow persistence later.

---

## 4. Core Domain Objects (Must Exist)

Your Low-Level Design **must explicitly model** the following:

- `Game`
- `Player`
- `Board`
- `Cell`
- `Snake`
- `Ladder`
- `Dice`
- `Move`
- `GameState`
- `GameResult`

Each entity must have a **clear responsibility**.

---

## 5. Design Pattern Expectations

The design should **naturally lead to** the use of the following patterns:

| Concern | Suggested Pattern |
|------|-------------------|
| Dice behavior | Strategy |
| Game creation | Factory |
| Move execution | Command |
| Game lifecycle | State |
| Game rules | Strategy / Chain of Responsibility |
| Event notifications (optional) | Observer |
| Supporting future games | Template Method / Abstract Factory |

Patterns must be **intentional and justified**, not forced.

---

## 6. Low-Level Design (LLD) Requirements

Your LLD should enable:

1. Clean **class UML diagrams**
2. Clear **interfaces vs implementations**
3. Proper use of:
    - Encapsulation
    - Composition
    - Immutability where possible
4. Thread-safety strategy:
    - Identify synchronization boundaries
    - Avoid global locks
5. Clear move validation and execution flow

---

## 7. UML Diagrams to Produce

You should be able to derive:

- **Class Diagram** (core entities & relationships)
- **Sequence Diagram** for:
    - Player taking a turn
    - Dice roll and movement resolution
- **State Diagram** for game lifecycle:
    - CREATED → IN_PROGRESS → FINISHED

---

## 8. High-Level Design (HLD) – Future Evolution

After completing local LLD, evolve the design to support:

### Distributed Gameplay
1. Online multiplayer games
2. REST / WebSocket-based move submission
3. Player reconnection to active games
4. Server-side move validation

---

### HLD Considerations
- Stateless vs stateful services
- Game state ownership and sharding
- Concurrency control per game
- Persistence and caching strategy
- Fault tolerance and recovery

---

## 9. Extension Scenarios (Must Be Supported Without Rewrites)

The design must support future features such as:
1. Multiple dice rolls on rolling a 6
2. Timed turns
3. Power-up cells
4. AI-controlled players
5. Tournament mode

Core engine logic **must not be modified** to add these features.

---

## 10. Implementation Checklist

Before coding:
- [ ] Interfaces defined first
- [ ] Responsibilities clearly separated
- [ ] Design patterns identified
- [ ] Thread-safety plan documented

After coding:
- [ ] Game logic correctness
- [ ] Unit-testable components
- [ ] Clean extensibility points
- [ ] Ready for HLD evolution

---

## Goal

This project is intended to act as:
- A **reference LLD implementation**
- A **system design interview practice problem**
- A foundation for evolving into a **distributed gaming platform**

Focus on **design clarity over cleverness**.
