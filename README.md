# Student Management System

A backend REST API for managing students, courses and enrollments using Spring Boot, Spring Data JPA, MySQL and JWT authentication.

## 🚀 Features

- Student Management
    - Add student
    - Get all students
    - Get student by ID
    - Update student
    - Delete student

- Course Management
    - Add course
    - Get all courses
    - Get course by ID
    - Update course
    - Delete course

- Enrollment Management
    - Enroll student into course
    - Get enrollments
    - Get enrollment by ID
    - Delete enrollment

- Authentication
    - User registration
    - User login
    - BCrypt password encryption
    - JWT authentication

- Authorization
    - USER role
    - ADMIN role
    - ADMIN-only write operations
    - Authenticated read operations

- Validation
    - Request validation
    - Email validation
    - Required field validation
    - Duplicate resource handling

- Exception Handling
    - Global exception handler
    - 400 Bad Request
    - 401 Unauthorized
    - 403 Forbidden
    - 404 Not Found
    - 409 Conflict

- API Documentation
    - Swagger UI
    - OpenAPI documentation
    - JWT authorization in Swagger

---

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java 24 | Programming Language |
| Spring Boot 3.5.5 | Backend Framework |
| Spring Web | REST APIs |
| Spring Data JPA | Database Access |
| Hibernate | ORM |
| MySQL | Database |
| Spring Security | Authentication & Authorization |
| JWT | Token-based Authentication |
| BCrypt | Password Encryption |
| Lombok | Boilerplate Reduction |
| Swagger / OpenAPI | API Documentation |
| Maven | Build Tool |
| IntelliJ IDEA | Development IDE |

---

## 🏗️ Project Architecture

```text
Controller
    ↓
Service
    ↓
DAO
    ↓
EntityManager / JPA
    ↓
Hibernate
    ↓
MySQL