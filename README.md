# iGaming Wallet API

REST API for iGaming wallet integration — handles balance, debit, credit and rollback operations between game providers and operators.

## Tech Stack

- Java 17 + Spring Boot 4
- PostgreSQL 16
- Spring Data JPA / Hibernate
- Docker Compose
- Swagger / OpenAPI

## Architecture

```
HTTP Request → Controller → Service → Repository → PostgreSQL
```

## Quick Start

Run with Docker Compose (no Java or PostgreSQL required):

```bash
docker-compose up --build
```

API will be available at `http://localhost:8080`

## API Documentation

Swagger UI: `http://localhost:8080/swagger-ui/index.html`

## Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| POST | /wallet/create/{userId}/{balance} | Create new wallet |
| GET | /wallet/{userId}/balance | Get player balance |
| POST | /wallet/{userId}/debit/{amount}/{txId} | Debit player balance |
| POST | /wallet/{userId}/credit/{amount}/{txId} | Credit player balance |
| POST | /wallet/{userId}/rollback/{txId}/{amount} | Rollback transaction |
| GET | /wallet/{userId}/history | Get transaction history |

## Example Requests

Create wallet:
```
POST http://localhost:8080/wallet/create/user-1/5000
```

Get balance:
```
GET http://localhost:8080/wallet/user-1/balance
```

Debit 500:
```
POST http://localhost:8080/wallet/user-1/debit/500/tx-001
```

Credit 1000:
```
POST http://localhost:8080/wallet/user-1/credit/1000/tx-002
```

Rollback:
```
POST http://localhost:8080/wallet/user-1/rollback/tx-001/500
```

Transaction history:
```
GET http://localhost:8080/wallet/user-1/history
```

## Error Handling

Insufficient funds returns 400:
```json
{
  "error": "Insufficient funds",
  "requested": 99999,
  "available": 5000
}
```

## Key Features

- **Idempotency** — duplicate transactions are ignored based on txId
- **Transaction history** — all operations are logged with timestamp
- **Docker Compose** — one command to run everything
- **Swagger UI** — interactive API documentation

## Status

✅ Wallet CRUD — done
✅ PostgreSQL — connected
✅ Transaction history — done
✅ Idempotency — done
✅ Docker Compose — done
✅ Swagger UI — done

