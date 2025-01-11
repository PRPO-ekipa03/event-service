# Event Service Microservice

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Architecture](#architecture)
- [Setup and Installation](#setup-and-installation)
  - [Prerequisites](#prerequisites)
  - [Clone the Repository](#clone-the-repository)
  - [Build the Project](#build-the-project)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
  - [Locally](#locally)
  - [Using Docker](#using-docker)
- [API Documentation](#api-documentation)
- [Endpoints Overview](#endpoints-overview)
  - [Event Endpoints](#event-endpoints)
- [Error Handling](#error-handling)

---

## Overview
The **Event Service** is a microservice for managing events, interacting with user and venue services to validate user and venue data, and creating reservations when necessary.

---

## Features
- CRUD operations for events.
- User and venue verification using external services.
- Event reservation creation.
- Status and location-based event queries.

---

## Technologies Used
- **Java 21** with **Spring Boot**
- **PostgreSQL** for database
- **OpenAPI/Swagger** for API documentation
- **Maven** for build management
- **Docker** for containerization

---

## Architecture
The service is divided into:
- **Controller Layer**: Handles REST API requests.
- **Service Layer**: Contains business logic.
- **Client Layer**: Interacts with external services.
- **Repository Layer**: Handles database operations.
- **Exception Handling**: Manages errors and exceptions.

---

## Setup and Installation

### Prerequisites
- Java Development Kit (JDK) 21
- Maven 3.9+
- Docker (optional)

### Clone the Repository
``bash
git clone https://github.com/PRPO-ekipa03/event-service.git
cd eventservice
``

### Build the Project
``bash
mvn clean package -DskipTests
``

---

## Configuration
Set the following configuration parameters in your environment or `application.properties` file:

``properties
# Application
spring.application.name=event-service
server.port=8083

# Database configuration
spring.datasource.url=jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA (Hibernate) configuration
spring.jpa.hibernate.ddl-auto=create
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true

# OpenAPI configuration
springdoc.api-docs.path=/events/api-docs
springdoc.swagger-ui.path=/events/swagger-ui

# Venue service
venue.service.url=http://${VENUE_SERVICE_HOST:localhost}:${VENUE_SERVICE_PORT:8084}/api/
``

---

## Running the Application

### Locally
Run the application with:
``bash
java -jar target/event-service.jar
``  

The service will start on port 8083 or the port specified in your configuration.

### Using Docker
To containerize the application:

1. **Build the Docker Image**:  
``bash
docker build -t event-service .
``  

2. **Run the Docker Container**:  
``bash
docker run -p 8083:8083 \
  -e DB_HOST=your-db-host \
  -e DB_PORT=your-db-port \
  -e DB_NAME=your-db-name \
  -e DB_USERNAME=your-db-username \
  -e DB_PASSWORD=your-db-password \
  -e VENUE_SERVICE_HOST=your-venue-service-host \
  -e VENUE_SERVICE_PORT=your-venue-service-port \
  event-service
``  

---

## API Documentation
The Swagger UI is available at:  
``  
http://localhost:8083/events/swagger-ui  
``  

---

## Endpoints Overview

### Event Endpoints
- **Create Event**: `POST /api/events`
- **Create Event with Reservation**: `POST /api/events/reservation`
- **Get Event by ID**: `GET /api/events/{eventId}`
- **Update Event**: `PUT /api/events/{eventId}`
- **Delete Event**: `DELETE /api/events/{eventId}`
- **Get Events by User**: `GET /api/events/users`

---

## Error Handling
The service provides detailed error responses for common issues:
- **404 Not Found**: Event, user, or venue not found.
- **400 Bad Request**: Validation errors.
- **500 Internal Server Error**: Unexpected server errors.
- **503 Service Unavailable**: Issues with external services (User or Venue Service).
