# Occupation Tracker Web Application

## Overview

Welcome to the Occupation Tracker web application! This application monitors the occupancy of a local gym by making API calls to retrieve the current number of people in the gym and storing this data in a PostgreSQL database. The application is composed of a frontend and a backend, and it is containerized using Docker Compose.

## Running the Application

To run the application, follow these steps:

1. Build the Docker images and start the containers:

   docker-compose build
   docker-compose up

## Accessing the Application

Once the application is running, you can access the Swagger UI to test the backend at the following URL:

    Swagger UI: http://localhost:8081/swagger-ui/index.html#/user-controller

## OpenTelemetry

The application incorporates OpenTelemetry for observability. The OpenTelemetry UI runs on port 16686. Access the OpenTelemetry UI at the following URL:


    OpenTelemetry UI: PORT: 16686

The OpenTelemetry UI runs on port 16686.

## Backend Servic
The Backend runs on port 8081. Make sure its public.

## Frontend Service
The Frontend UI runs on port 4200.


## Database
The Postgres runs on port 5432.
The application utilizes a PostgreSQL database, and the connection settings are specified in the application.properties file.



