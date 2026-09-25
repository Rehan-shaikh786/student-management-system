# 🎓 Student Management System

> A secure, scalable and production-style RESTful Student Management System built with Spring Boot, JPA, Hibernate, MySQL, Spring Security and JWT Authentication.

![Java](https://img.shields.io/badge/Java-24-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.5-brightgreen)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6.x-green)
![JPA](https://img.shields.io/badge/JPA-Hibernate-blue)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![JWT](https://img.shields.io/badge/JWT-Authentication-purple)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-green)
![Maven](https://img.shields.io/badge/Maven-Build%20Tool-red)
![License](https://img.shields.io/badge/License-MIT-yellow)

---

## 📌 About the Project

**Student Management System** is a backend REST API application developed using **Java and Spring Boot**.

The system is designed to manage students, courses and enrollments while providing secure authentication and role-based authorization.

The project demonstrates how a modern Java backend application can be structured using:

- RESTful APIs
- Spring Boot
- Spring Security
- JWT Authentication
- Role-Based Access Control
- JPA / Hibernate
- MySQL
- DAO Layer
- Service Layer
- Controller Layer
- DTOs
- Validation
- Global Exception Handling
- Swagger / OpenAPI Documentation

The application follows a clean layered architecture to keep business logic, database operations and API handling separated.

---

# 🎯 Project Objectives

The main objectives of this project are:

- Build a complete RESTful backend application.
- Implement Student CRUD operations.
- Implement Course CRUD operations.
- Manage student course enrollments.
- Implement secure user registration and login.
- Implement JWT-based authentication.
- Implement role-based authorization.
- Protect APIs using Spring Security.
- Encrypt user passwords using BCrypt.
- Handle invalid requests using validation.
- Handle application exceptions globally.
- Document APIs using Swagger/OpenAPI.
- Follow a clean and maintainable project architecture.

---

# ✨ Key Features

## 👨‍🎓 Student Management

- Add new students
- View all students
- View student by ID
- Update student information
- Delete student
- Validate student data
- Handle student-not-found scenarios

## 📚 Course Management

- Add new courses
- View all courses
- View course by ID
- Update course information
- Delete courses
- Validate course data
- Prevent duplicate course information

## 📝 Enrollment Management

- Enroll a student into a course
- View all enrollments
- View enrollment by ID
- Delete enrollment
- Maintain student-course relationships

## 🔐 Authentication

- User registration
- User login
- BCrypt password encryption
- JWT token generation
- JWT token validation
- Secure protected APIs

## 👮 Role-Based Authorization

The application supports different user roles.

### USER

Users can access protected GET APIs.

### ADMIN

Administrators can perform management operations including:

- GET
- POST
- PUT
- DELETE

This provides a basic Role-Based Access Control system.

---

# 🛡️ Security Features

Security is one of the major parts of this project.

The application uses:

- Spring Security
- JWT Authentication
- BCrypt Password Encryption
- Stateless Authentication
- Role-Based Authorization
- Bearer Token Authentication
- Protected REST APIs
- Custom 401 Unauthorized response
- Custom 403 Forbidden response

## Authentication Flow

```text
                    ┌──────────────┐
                    │     User     │
                    └──────┬───────┘
                           │
                           ▼
                    ┌──────────────┐
                    │    Login     │
                    └──────┬───────┘
                           │
                           ▼
                    ┌──────────────┐
                    │ JWT Generated│
                    └──────┬───────┘
                           │
                           ▼
                 ┌────────────────────┐
                 │ Authorization:     │
                 │ Bearer <JWT>       │
                 └─────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │ JwtAuthentication   │
                │ Filter              │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │ Spring Security     │
                │ SecurityContext     │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │ Role Authorization  │
                └──────────┬──────────┘
                           │
                           ▼
                     REST API