# BikeGarage

BikeGarage is the Spring Boot backend for a bike service center management platform. It gives service center owners a REST API to manage mechanics, customers, vehicles, and service records, with JWT-based authentication and Google Sign-In support. It is designed to be paired with a mobile client (see the companion [bikeBuilders](https://github.com/itswael/bikeBuilders) Android app).

## Tech Stack

- **Java 17** / **Spring Boot 3.5**
- **Spring Data JPA** with **MySQL** (via `mysql-connector-j`)
- **Flyway** for database migrations
- **Spring Security** with **BCrypt** password hashing and stateless **JWT** authentication (`jjwt`)
- **Google Sign-In / OAuth 2.0** (`google-api-client`) for social login
- **Lombok**
- **Maven** (with the Maven Wrapper)

## Features

- **Authentication** — username/password login, user registration, and Google Sign-In (including a full OAuth authorization-code flow), all issuing a signed JWT
- **Role-based access control** — distinct `OWNER`, `MECHANIC`, and `CUSTOMER` roles with endpoint-level authorization rules (`SecurityConfig`)
- **User management** — CRUD operations on user accounts and profiles
- **Vehicle management** — register and manage customer vehicles
- **Service tracking** — create and query service records for a vehicle, including work logs and invoicing entities
- **Centralized error handling** — custom exceptions (`ResourceNotFoundException`, `DuplicateResourceException`, `UnauthorizedActionException`, etc.) mapped through a global exception handler

## Architecture

Layered MVC structure: `controllers` → `services` → `repositories`, with `dtos` for API contracts and `mapper` classes to convert between entities and DTOs. Domain models (`User`, `Vehicle`, `ServiceRec`, `ServiceCentre`, `Invoice`, `WorkLog`) live under `models`, with supporting enums for roles and statuses.

Design documents (HLD, LLD, SQL schema, SRS) are available in the [`documentation`](./documentation) folder.

## Getting Started

Prerequisites: JDK 17 and a running MySQL instance.

```bash
git clone https://github.com/itswael/BikeGarage.git
cd BikeGarage

export DB_URL="jdbc:mysql://localhost:3306/bikegarage"
export DB_USERNAME="root"
export DB_PASSWORD="your-mysql-password"
export JWT_SECRET="your-own-random-secret"

./mvnw clean install
./mvnw spring-boot:run
```

The API is served under `/api` (e.g. `/api/auth/login`, `/api/auth/google`, `/api/services/**`, `/api/vehicles/**`).

> **Note:** database credentials and the JWT secret are read from environment variables (`DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `JWT_SECRET`) — nothing sensitive is committed in `application.properties`.

## Contributing

- Fork the repository
- Create a feature branch
- Commit your changes
- Open a pull request

## License

This project is licensed under the Wael Non-Commercial Attribution License (WNCA) — free to use, modify, and share for non-commercial purposes with attribution. See [LICENSE](./LICENSE) for full terms.
