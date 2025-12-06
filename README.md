# ecommerce-backend

# 🛒 E-Commerce Backend — Microservices Architecture

A modular **Spring Boot 3 + Spring Cloud** backend for an e-commerce platform built with a **microservices** design, **Eureka service discovery**, and **JWT-based authentication**.

---

## Project Structure
ecommerce-backend/ \
├── discovery-server/ Eureka service registry \
├── auth-service/ Authentication, JWT, and user management \
├── catalogue-service/ Product catalogue, categories, search \
├── order-service/ Orders, checkout, history \
├── payment-service/ Payment integration and tracking \
├── shared-library/ Common DTOs, models, and utilities \


---

## Tech Stack

| Layer | Technology |
|-------|-------------|
| Language | Java 17 |
| Framework | Spring Boot 3.x |
| Service Discovery | Spring Cloud Netflix Eureka |
| Gateway | Spring Cloud Gateway |
| Security | Spring Security + JWT |
| Inter-Service Communication | REST / OpenFeign |
| Database | H2 (dev) / PostgreSQL (prod ready) |
| Build Tool | Maven |

---

## Architecture Overview

All services register with **Eureka** (`discovery-server`), and communicate via logical service IDs.  
Optionally, the **API Gateway** provides a single entry point with centralized JWT validation.

            ┌───────────────┐
            │   Frontend    │
            └──────┬────────┘
                   │
                   ▼
            ┌─────────────────┐
            │   API Gateway   │
            └──────┬──────────┘
                   │
       ┌──────────────────────────┐
       │   Discovery Server (8761)│
       └──────┬─────────┬─────────┘
              │         │
    ┌─────────▼─-┐ ┌────▼───────-─┐ ┌───────────▼───┐ ┌───────────▼────┐
    │auth-service│ │catalogue-svc │ │order-service  │ │payment-service │
    └────────────┘ └──────────────┘ └───────────────┘ └────────────────┘


---

## Service Responsibilities

### **auth-service**
Handles user registration, login, JWT token creation and validation.

### **catalogue-service**
Manages product catalogue, categories, and product search.

### **order-service**
Handles order placement, order history, and communicates with  
`catalogue-service` and `payment-service` via Feign clients.

### **payment-service**
Initiates and validates payments, listens to callbacks, updates order status.

### **discovery-server**
Central registry using Eureka.

### **api-gateway** *(optional but recommended)*
Routes external requests to internal services and validates JWTs.

---

## How to Run Locally

### Prerequisites
- JDK 17+
- Maven
- (optional) Docker / Docker Compose

### Build the project
```bash
  mvn clean install
```

### Start Eureka Discovery Server
```
cd discovery-server
mvn spring-boot:run
```

### Start Microservices
Open separate terminals and run:
```
cd ../auth-service && mvn spring-boot:run
cd ../catalogue-service && mvn spring-boot:run
cd ../order-service && mvn spring-boot:run
cd ../payment-service && mvn spring-boot:run
```

### Start API Gateway
```
cd ../api-gateway
mvn spring-boot:run
```

### Access Endpoints through Gateway
```
http://localhost:8080/api/auth/**
http://localhost:8080/api/catalogue/**
http://localhost:8080/api/orders/**
http://localhost:8080/api/payments/**
```

### Security & Authentication Flow
1. User logs in via auth-service and receives a JWT.
2. Client sends Authorization: Bearer <token> in every request.
3. API Gateway / services validate the JWT.
4. Feign clients (used by order-service) forward the token to downstream services.

### Default Ports


| Service           | Port |
|-------------------|------|
| discovery-server	 | 8761 |
| api-gateway	      | 8080 |
| auth-service	| 8081 |
| catalogue-service | 8082 |
| order-service	| 8083 |
| payment-service	| 8084 |

### Development Notes
* Each service has its own application.yml defining its port and Eureka client config.
* Common entities and DTOs reside in shared-library.
* Database credentials, JWT secrets, and API keys should be externalized via environment variables.
* Use @EnableDiscoveryClient in each microservice main class.
* Use spring-cloud-starter-openfeign for inter-service calls.

### Testing
1. Hit the Auth API to register and login.
2. Copy the JWT token and access catalogue/order endpoints through the Gateway.
3. Check Eureka dashboard → each service should appear as UP.

### Future Enhancements
|Feature	| Description |
|-------------------|------|
| API Gateway | Enhancements	Rate limiting, Swagger aggregation, CORS fine-tuning |
| Resilience4j |	Circuit breakers and retry policies for Feign clients |
| DB Migration |	Integrate Flyway or Liquibase for schema versioning |
| Logging & Tracing	| Centralized logging and distributed tracing (Zipkin/OpenTelemetry) |
| Async Messaging	| Use Kafka or RabbitMQ for order/payment events |
| Docker & CI/CD	| Containerize and automate build pipeline |

### API Documentation
Enable SpringDoc OpenAPI in each service to view Swagger UI:
```
auth-service:      http://localhost:8081/swagger-ui.html
catalogue-service: http://localhost:8082/swagger-ui.html
order-service:     http://localhost:8083/swagger-ui.html
payment-service:   http://localhost:8084/swagger-ui.html
```
Contributors

|Name              |Role|
|-------------------|------|
|Ankit Pangasa|Project Owner / Developer|

### Summary
* Modular Spring Boot microservices
* Eureka service discovery
* JWT authentication & authorization
* OpenFeign for inter-service communication
* API Gateway support
* Production-ready structure with clear separation of concerns





