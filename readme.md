# Fullstack Login System

A full-stack login system featuring a Spring Boot backend (Java) and a modern JavaScript frontend, containerized with Docker for easy deployment.

## Features

- User registration and login
- JWT-based authentication
- Password hashing with bcrypt
- CORS configuration
- RESTful API
- Modular frontend with clean UI
- Dockerized backend and frontend for easy deployment

## Project Structure

```
fullstack-login/
├── Backend/
│   ├── Dockerfile
│   ├── src/
│   └── ...
├── Frontend/
│   ├── Dockerfile
│   ├── src/
│   └── ...
├── docker-compose.yml
└── README.md
```

## Getting Started

### Prerequisites

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

### Running the Project

1. **Clone the repository:**
    ```sh
    git clone https://github.com/yourusername/fullstack-login.git
    cd fullstack-login
    ```

2. **Build and start the containers:**
    ```sh
    docker-compose up --build
    ```

3. **Access the application:**
    - Frontend: [http://localhost:3000](http://localhost:3000)
    - Backend API: [http://localhost:8080](http://localhost:8080)

## Environment Variables

- Configure backend and frontend environment variables as needed.
- Use `.env` files or Docker secrets for sensitive data.

## API Endpoints

- `POST /api/auth/register` — Register a new user
- `POST /api/auth/login` — Login and receive JWT token
- `GET /api/user/me` — Get current user info (requires JWT)

## Security

- Passwords are hashed using bcrypt.
- JWT tokens are used for authentication.
- CORS is configured for secure cross-origin requests.

## Development

- Backend: Java, Spring Boot
- Frontend: JavaScript (Vite, Vanilla JS or your framework)
- Containerization: Docker, Docker Compose

## License

This project is licensed under the MIT License.

---

**Contributions are welcome!**  
Feel free to open issues or submit pull requests.
