# Real-Time Stock Data Service

This project provides a real-time stock data service built using Spring Boot, with MongoDB and Redis for storage and caching. The service is containerized with Docker and uses Docker Compose to simplify deployment.

---

## Features
- Real-time stock data retrieval using Alpha Vantage API.
- Caching for performance with Redis.
- Data persistence with MongoDB.
- Containerized deployment using Docker.

---

## Prerequisites
1. [Docker](https://www.docker.com/get-started) - Required to run containers.
2. [Docker Compose](https://docs.docker.com/compose/install/) - For orchestrating multiple containers.

---

## How to Run the Application
Follow these steps to set up and run the application.

### 1. Clone the Repository
Download the repository:
```bash
git clone https://github.com/ssv2612/realtime-stockdata-service.git
cd realtime-stockdata-service
```

### 2. Start the Application
The repository has the docker-compose file, which will start all services(MongoDB, Redis and Stock service application) using:
```bash
docker-compose up
```
The above command:
- Pulls the pre-built docker image from docker hub. 
- Starts MongoDB and redis containers
- Starts the Stock service container

### 3. Access the Application
- You can either go to your browser and go to: http://localhost:8080/stock/ticker/<ticker-symbol>
- Replace the ticker symbol with any valid ticker to fetch the real time price for the same.
- Example: http://localhost:8080/stock/ticker/AAPL
- This can also be tested using Postman.

### 4. To stop the application
```bash
docker-compose down
```
This stops all the running containers

### 5. Logs and Debugging
To check if there are any issues with any of the containers
```bash
docker logs <container_name>
```
Example: docker logs mongodb

- Docker Hub Image: https://hub.docker.com/repository/docker/ssv2612/stock-data-service