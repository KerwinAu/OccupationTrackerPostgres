# Web Application

This is a web application that consists of a frontend and a backend.

## swagger
http://localhost:8081/swagger-ui/index.html#/user-controller

## Frontend

The frontend is responsible for displaying the user interface of the web application. To run the frontend, simply open the `index.html` file in your web browser.
ng update @angular/cli@latest   
ng serve 

## Backend

The backend is responsible for handling the business logic and data storage of the web application. To run the backend, you need to have Maven installed on your system. Then, navigate to the root directory of the backend and run the following command:

mvn clean install
mvn spring-boot:run

## dockercompose
build images 
docker-compose build 
run container
docker-compose up