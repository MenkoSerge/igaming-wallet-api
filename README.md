# iGaming Wallet API

REST API for iGaming wallet integration — handles balance, debit, credit and rollback operations between game providers and operators.

## Tech Stack

- Java 17 + Spring Boot 3
- PostgreSQL
- Docker Compose
- Swagger / OpenAPI

## Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | /wallet/{userId}/balance | Get player balance |
| POST | /wallet/debit | Debit player balance |
| POST | /wallet/credit | Credit player balance |
| POST | /wallet/rollback | Rollback transaction |

## Run locally
```bash
docker-compose up -d
./mvnw spring-boot:run
```

## Status

 In progress
