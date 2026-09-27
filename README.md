# Task Manager CLI

A console-based personal task manager built with **pure Java SE**.  
Tasks are saved to XML, deadlines tracked with `LocalDate`, and reports generated with Stream API.

---

## Features

- Add, update, and delete tasks
- Priority levels (LOW / MEDIUM / HIGH)
- Categories (WORK / PERSONAL / STUDY / OTHER)
- Deadline tracking with overdue detection
- Reports: Today's tasks, Overdue, Completed
- Persistent storage via XML (JAXB)

---

## Tech Stack

| Topic | Implementation |
|---|---|
| Language | Java 25 (SE only) |
| Build Tool | Maven |
| XML Parsing | JAXB |
| Date/Time | `LocalDate`, `LocalDateTime` |
| Reporting | Stream API, Lambda |
| Boilerplate | Lombok |

---

## Run

```bash
mvn compile exec:java -Dexec.mainClass="app.Main"
```
