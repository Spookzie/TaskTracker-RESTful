# Task Tracker (Java + Spring Boot)

**Task Tracker** is a backend system for managing tasks within task lists. Built using Java 24 and Spring Boot, the project emphasizes clean code, layered architecture, and maintainability.

---

## 🛠 Tech Stack

- Java 24  
- Spring Boot  
- PostgreSQL (local)
- Spring Data JPA  
- Custom Mappers (DTO ↔ Entity)  
- Maven

---

## 🧩 Architecture Overview

This project follows a modular, multi-layered architecture:

- **DTOs & Entities**: Clear separation to keep API models isolated from persistence logic.
- **Custom Mappers**: Manual mapping methods ensure full control over data transformation.
- **Service Layer**: Encapsulates business logic for better maintainability and scalability.
- **Repository Layer**: Built using `JpaRepository` for data access and query abstraction.
- **Global Exception Handling**: Centralized error management using `@ControllerAdvice`.
- **Transaction Management**: Leveraged `@Transactional` on delete operations to ensure data consistency.

---

## 🔧 Features

- Create, update, and delete tasks within task lists  
- Clean and modular folder structure (`controllers`, `services`, `models`, `mappers`)  
- Consistent use of best practices in exception handling and data access  
- Code is well-documented and easy to extend

---

## 🖥 Frontend

The frontend uses a pre-made MVP template to simulate interaction. Backend logic is the main focus of this project.

---

## 🚀 Getting Started

### Prerequisites:
- Java 21 or higher (Java 24 recommended)
- Maven
- PostgreSQL (ensure the DB and credentials match `application.properties`)

### Run the Project:
```bash
mvn spring-boot:run
