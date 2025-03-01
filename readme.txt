Project Setup Instructions
This document provides step-by-step instructions to set up and run the 
Travel Itinerary Management System using Docker Compose. 
The project includes microservices for hotels, transportation, and itineraries, and uses Eureka for service discovery and OpenFeign for load balancing.

Prerequisites
Install Docker:

Download and install Docker from the official website: https://www.docker.com/products/docker-desktop.
Ensure Docker is running on your machine.

Steps to Set Up and Run the Project
Build the Docker Images:

Build all the services defined in the docker-compose.yml file:
docker-compose build
Run the Application:

Start all the services:
docker-compose up
(NOTE: you might have to wait a minute or two for all the services to register to eureka)

Access the Application:

Eureka Dashboard:
URL: http://localhost:8761
API Gateway:
URL: http://localhost:8080
Front-End Interface:
URL: http://localhost:8083/hotels
Swagger Documentation:
Each REST service has its Swagger UI available at:
Boat REST Service: http://localhost:8081/swagger-ui.html
Flight REST Service: http://localhost:8082/swagger-ui.html
Hotel REST Service: http://localhost:8084/swagger-ui.html
Itinerary REST Prosumer: http://localhost:8085/swagger-ui.html
Transportation REST Prosumer: http://localhost:8086/swagger-ui.html

Scaling Microservices
To scale a specific microservice, use the --scale option with docker-compose up. For example:
docker-compose up --scale transportation-rest-prosumer=3
This will deploy three instances of the transportation-rest-prosumer service. Load balancing is handled automatically via Eureka and OpenFeign.

Stopping the Application
To stop all running containers, use:
docker-compose down
