# Expense Tracker API

A RESTful API for managing personal expenses, built with Spring Boot and MySQL. Features user authentication with JWT and expense filtering by date ranges.

## Features
- User signup and login with JWT authentication
- Create, read, update, and delete expenses
- Filter expenses by past week, month, 3 months, or custom date range
- Predefined categories: Groceries, Leisure, Electronics, etc.

## Technologies
- Spring Boot
- Spring Data JPA
- Spring Security (JWT)
- MySQL
- Maven

## Setup
1. Clone the repository: `git clone <your-repo-url>`
2. Configure MySQL database in `application.properties`
3. Run `mvn spring-boot:run`
4. Access the API at `http://localhost:8080`
