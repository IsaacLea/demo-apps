# JWT Demo Application

This is a demo application showcasing the implementation of JSON Web Token (JWT) authentication using Spring Boot.

## Features
- User authentication and authorization using JWT.
- Secure API endpoints.
- Custom user details service.
- Token validation and filtering.

## Project Structure
```
src/
  main/
    java/
      com/
        example/
          demo/
            controller/       # REST controllers
            model/            # Data models
            repository/       # Data access layer
            security/         # Security configurations and utilities
            service/          # Business logic layer
  resources/
    application.properties   # Application configuration
```

## Prerequisites
- Java 17 or higher
- Maven 3.8 or higher

## Getting Started

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd jwt-demo
   ```

2. Build the project:
   ```bash
   ./mvnw clean install
   ```

3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

4. Access the application at `http://localhost:8080`.

## API Endpoints

### Authentication
- `POST /api/auth/login` - Authenticate user and return JWT.

### Test Endpoints
- `GET /api/test/user` - Accessible by users with `USER` role.
- `GET /api/test/admin` - Accessible by users with `ADMIN` role.

## Configuration

Update the `application.properties` file to configure database and other settings.

## References
This project is based on:
https://medium.com/@victoronu/implementing-jwt-authentication-in-a-simple-spring-boot-application-with-java-b3135dbdb17b
