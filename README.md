# iGaming Wallet API

REST API for iGaming wallet integration — handles balance, debit, credit and rollback operations between game providers and operators.

## Tech Stack

- Java 17 + Spring Boot 4
- PostgreSQL 16
- Spring Data JPA / Hibernate
- Docker Compose (coming soon)
- Swagger / OpenAPI (coming soon)

## Architecture
HTTP Request → Controller → Service → Repository → PostgreSQL

## Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| POST | /wallet/create/{userId}/{balance} | Create new wallet |
| GET | /wallet/{userId}/balance | Get player balance |
| POST | /wallet/{userId}/debit/{amount} | Debit player balance |
| POST | /wallet/{userId}/credit/{amount} | Credit player balance |
| POST | /wallet/{userId}/rollback/{txId}/{amount} | Rollback transaction |

## Example Requests

Create wallet: POST http://localhost:8080/wallet/create/user-1/5000
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

Requirements:
- Java 17+
- PostgreSQL 16

Configure database in `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/igaming_wallet
spring.datasource.username=wallet_user
spring.datasource.password=wallet_pass
```

Run:
```bash
./mvnw spring-boot:run
```

## Status

Wallet CRUD — done  
PostgreSQL — connected  
Transaction history — in progress  
Idempotency — in progress  
Docker Compose — coming soon  
Swagger docs — coming soon




