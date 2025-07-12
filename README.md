# Auth-Service
**Project Summary**

This is a Spring Boot-based authentication service that provides RESTful APIs for user registration, login, account verification, and resending verification codes. It uses JWT for secure authentication and supports email-based account verification. The project follows best practices for environment variable management and exception handling, making it suitable for integration into larger systems requiring user authentication and authorization.

**Key Features:**
- User sign-up and login endpoints
- Email verification with code resend functionality
- JWT-based authentication
- Secure password handling
- Custom exception handling
- Easily configurable via environment variables

**Tech Stack:**  
Java, Spring Boot, Spring Security, Maven

**Typical Endpoints:**
- `/auth/signup` — Register a new user
- `/auth/login` — User login
- `/auth/verify` — Verify account with code
- `/auth/resend` — Resend verification code
