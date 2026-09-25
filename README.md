# Student Management System

> A secure RESTful Student Management System built with Spring Boot, JPA, MySQL and JWT authentication.

![Java](https://img.shields.io/badge/Java-24-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.5-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![JWT](https://img.shields.io/badge/JWT-Authentication-purple)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-green)
![License](https://img.shields.io/badge/License-MIT-yellow)

---

## 📌 About the Project

Student Management System is a RESTful backend application developed using Spring Boot.

The application provides APIs for managing:

- Students
- Courses
- Enrollments
- User authentication
- Role-based authorization

The project follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
DAO
    ↓
JPA / EntityManager
    ↓
MySQL