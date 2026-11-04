# JobPilot

![CI](https://github.com/felipeNeves93/jobpilot/actions/workflows/ci.yml/badge.svg)

A job-search tracker (companies, job postings, applications and interview stages) built with Java 25 and Spring Boot 4. It is designed so Claude can use it as a set of tools through MCP (Model Context Protocol).

> Status: **Phase 0 — project setup.** The app starts, connects to PostgreSQL and exposes a health check. Features are added phase by phase (see [Roadmap](#roadmap)).

## Tech stack

| Layer | Choice |
| --- | --- |
| Language | Java 25 (LTS) |
| Framework | Spring Boot 4.1 (Spring Framework 7) |
| Persistence | Spring Data JPA, Hibernate 7, Flyway |
| Database | PostgreSQL 17 |
| Testing | JUnit 5, Testcontainers |
| Build / CI | Maven Wrapper, GitHub Actions |
| Local infra | Docker Compose (started automatically by Spring Boot) |

## Prerequisites

- JDK 25 (for example `sdk install java 25-tem` with [SDKMAN](https://sdkman.io))
- Docker (running)

## Run it

```bash
git clone https://github.com/felipeNeves93/jobpilot.git
cd jobpilot
./mvnw spring-boot:run
```

Spring Boot's Docker Compose support starts the PostgreSQL container from `compose.yaml` and configures the datasource automatically. You don't need to set up a database by hand.

Check that it's up:

```bash
curl localhost:8080/actuator/health
```

You should see `"status":"UP"` with a `db` component reporting `PostgreSQL`.

## Run the tests

```bash
./mvnw verify
```

Tests run against a real PostgreSQL 17 instance started by Testcontainers (Docker Compose isn't used in tests). CI runs the same command on every push and pull request.

## Configuration notes

- `spring.jpa.hibernate.ddl-auto=validate`: Hibernate never changes the schema. All schema changes go through Flyway migrations in `src/main/resources/db/migration`.
- `spring.jpa.open-in-view=false`: database access stays inside the service layer's transactions.
- Local database: `jobpilot` / `jobpilot` on `localhost:5432` (dev only).

## Roadmap

- [x] Phase 0 — Setup: Boot 4.1 on Java 25, PostgreSQL via Compose, CI
- [ ] Phase 1 — Domain model with JPA and Hibernate 7
- [ ] Phase 2 — REST API
- [ ] Phase 3 — Testing
- [ ] Phase 4 — Modern Java features
- [ ] Phase 5 — MCP server
- [ ] Phase 6 — Claude integration
- [ ] Phase 7 — Claude Code repo automation
- [ ] Phase 8 — Production readiness
- [ ] Phase 9 — Live job search stream