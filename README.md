# Zob - Incident Tracking System

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-25-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

Zob is a comprehensive **Observation and Incident Tracking System** designed to monitor services, track incidents, and maintain service health across distributed systems. Built with Spring Boot, it provides automated service discovery, continuous health monitoring, and intelligent incident management.

## 🌟 Features

- **Service Discovery & Registry** - Automatic and manual service registration with endpoint tracking
- **Health Monitoring** - Continuous canary-based health checks and probes
- **Incident Management** - Automated incident creation, tracking, and resolution
- **Real-time Notifications** - Event-driven architecture using RabbitMQ
- **REST API** - Comprehensive RESTful APIs for all operations
- **API Documentation** - Interactive Swagger/OpenAPI documentation
- **Scheduled Tasks** - Automated periodic health checks and incident resolution
- **Security** - JWT-based authentication and authorization

## 🏗️ Architecture

Zob is built with a modular architecture consisting of three core services:

### 1. Regina - Service Discovery & Registry
> *"Think of Regina like the Class-list"*

Regina manages the service registry and handles service discovery operations.

**Core Functionality:**
- Service Registry - Manual and automatic service registration
- Service Discovery - Active service health checks
- Endpoint Management - Add and manage service endpoints
- Status Updates - Track service and endpoint status changes
- Service Listing - View all registered services

**API Endpoints:**
- `POST /api/v1/discover/registerService` - Register a new service
- `POST /api/v1/discover/endpoint/add` - Add endpoint to existing service
- `PATCH /api/v1/discover/service/status` - Update service status
- `PATCH /api/v1/discover/endpoint/status` - Update endpoint status

### 2. Monica - Monitoring & Canary Service
> *"Think of Monica like the Class Rep"*

Monica performs continuous health monitoring through canary checks and probes.

**Core Functionality:**
- Canary Registration - Register health check canaries
- Probe Execution - Run health probes at configured intervals
- Service Health Aggregation - Calculate overall service health
- Result Storage - Maintain probe result history

### 3. Ima - Incident Management Service
> *"Think of Ima like the student academic records"*

Ima manages the lifecycle of incidents from creation to resolution.

**Core Functionality:**
- Incident Creation - Create incident records for service failures
- Status Management - Track incident lifecycle and updates
- Auto-resolution - Automatically resolve incidents when services recover
- History Tracking - Maintain complete incident history
- Manual Operations - Support manual incident resolution and closure

**API Endpoints:**
- `POST /api/v1/incident/create` - Create new incident
- `PATCH /api/v1/incident/status/update` - Update incident status
- `GET /api/v1/incident/incidents/active/{serviceID}` - Get active incidents
- `GET /api/v1/incident/history/{serviceID}` - Get incident history
- `PATCH /api/v1/incident/resolve` - Resolve an incident
- `PATCH /api/v1/incident/close` - Close an incident

## 🛠️ Technology Stack

- **Framework:** Spring Boot 3.5.6
- **Language:** Java 25
- **Database:** MySQL (with Flyway migrations)
- **Message Queue:** RabbitMQ
- **Security:** Spring Security with JWT
- **API Documentation:** SpringDoc OpenAPI 3 (Swagger)
- **Object Mapping:** MapStruct
- **Build Tool:** Maven
- **Reactive:** Spring WebFlux

### Key Dependencies
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Security
- Spring Boot Starter AMQP (RabbitMQ)
- Spring Boot Starter WebFlux
- Flyway (Database Migrations)
- Lombok (Code Generation)
- MapStruct (Object Mapping)
- JJWT (JWT Authentication)

## 📋 Prerequisites

- Java 25 or higher
- Maven 3.6+
- MySQL 8.0+
- RabbitMQ 3.x
- Docker & Docker Compose (optional, for containerized setup)

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/Tendwa-T/Zob-server.git
cd Zob-server
```

### 2. Configure Environment Variables

Create a `.env` file in the root directory (use `.env.example` as template):

```env
JWT_SECRET=your_jwt_secret_key_here
DB_USER=your_database_username
DB_PASS=your_database_password
```

### 3. Start Dependencies

#### Using Docker Compose (Recommended)

```bash
docker-compose up -d
```

This will start:
- RabbitMQ on port 5672

#### Manual Setup

Ensure MySQL and RabbitMQ are running:

- **MySQL:** Running on `localhost:3306`
- **RabbitMQ:** Running on `localhost:5672`

### 4. Database Setup

The application uses Flyway for database migrations. The database will be created automatically on first run.

Database name: `zobV1`

### 5. Build the Application

```bash
./mvnw clean install
```

### 6. Run the Application

```bash
./mvnw spring-boot:run
```

Or run with specific profile:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

The application will start on the default Spring Boot port (typically 8080).

## 📚 API Documentation

Once the application is running, access the interactive API documentation:

```
http://localhost:8080/swagger-ui.html
```

Or access the OpenAPI JSON specification:

```
http://localhost:8080/v3/api-docs
```

## 🔧 Configuration

### Application Profiles

The application supports multiple profiles:

- **dev** - Development profile (default)

Configure the active profile in `application.yaml`:

```yaml
spring:
  profiles:
    active: dev
```

### Database Configuration

Configure database connection in `application-dev.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/zobV1?createDatabaseIfNotExist=true
    username: ${DB_USER}
    password: ${DB_PASS}
```

### JWT Configuration

JWT settings in `application.yaml`:

```yaml
spring:
  jwt:
    secret: ${JWT_SECRET}
    accessTokenExpiration: 7200      # 2 hours
    refreshTokenExpiration: 604800   # 7 days
    redirectTokenExpiration: 600     # 10 minutes
```

### RabbitMQ Configuration

RabbitMQ is configured via `compose.yaml`:

```yaml
services:
  rabbitmq:
    image: 'rabbitmq:latest'
    environment:
      - 'RABBITMQ_DEFAULT_PASS=password'
      - 'RABBITMQ_DEFAULT_USER=tendwa'
```

## 🧪 Running Tests

```bash
./mvnw test
```

## 📦 Building for Production

Create a production-ready JAR:

```bash
./mvnw clean package -DskipTests
```

The JAR file will be created in the `target/` directory.

Run the production JAR:

```bash
java -jar target/zob-backend-0.0.1-SNAPSHOT.jar
```

## 🔐 Security

The application uses JWT-based authentication. Key security features:

- JWT token-based authentication
- Secure endpoints with Spring Security
- Public access to Swagger documentation
- Token expiration and refresh mechanism

## 📁 Project Structure

```
src/main/java/com/tendwa/zobbackend/
├── Ima/              # Incident Management Service
│   ├── controllers/  # REST controllers
│   ├── services/     # Business logic
│   ├── repositories/ # Data access layer
│   ├── entities/     # JPA entities
│   ├── dtos/         # Data transfer objects
│   └── mappers/      # MapStruct mappers
├── Monica/           # Monitoring & Canary Service
│   ├── services/     # Business logic
│   ├── repositories/ # Data access layer
│   ├── entities/     # JPA entities
│   └── mappers/      # MapStruct mappers
├── Regina/           # Service Discovery & Registry
│   ├── controllers/  # REST controllers
│   ├── services/     # Business logic
│   ├── repositories/ # Data access layer
│   ├── entities/     # JPA entities
│   └── mappers/      # MapStruct mappers
└── generic/          # Shared components
    ├── config/       # Configuration classes
    ├── enums/        # Enumerations
    ├── exceptions/   # Custom exceptions
    ├── responses/    # Common response objects
    └── rules/        # Security rules
```

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📧 Contact

**Support:** tiruskhamasi@gmail.com

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- OpenAPI/Swagger for API documentation tools
- The open-source community for various dependencies

---

**Version:** v0.1  
**Status:** Active Development
