# iGaming Wallet API

REST API for iGaming wallet integration — handles balance, debit, credit and rollback operations between game providers and operators.

## Tech Stack

- Java 17 + Spring Boot 4
- PostgreSQL (H2 for local dev)
- Docker Compose
- Swagger / OpenAPI (coming soon)

## Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | /wallet/{userId}/balance | Get player balance |
| POST | /wallet/{userId}/debit/{amount} | Debit player balance |
| POST | /wallet/{userId}/credit/{amount} | Credit player balance |
| POST | /wallet/{userId}/rollback/{txId}/{amount} | Rollback transaction |

## Example Requests

Get balance: GET http://localhost:8080/wallet/user-1/balance

Debit 500: POST http://localhost:8080/wallet/user-1/debit/500

Credit 1000: POST http://localhost:8080/wallet/user-1/credit/1000

Rollback transaction: POST http://localhost:8080/wallet/user-1/rollback/tx-001/500


## Error Handling

Insufficient funds returns 400:
```json
{
  "error": "Insufficient funds",
  "requested": 99999,
  "available": 5000
}
```

## Run locally

```bash
./mvnw spring-boot:run
```

## Status

🚧 In progress — PostgreSQL integration coming next
