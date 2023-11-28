# Dockerfile-backend
FROM maven:3.8.4-jdk-11 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/webshop-0.0.1-SNAPSHOT.jar ./
COPY wait-for-it.sh ./
#COPY sql ./sql

RUN chmod +x /app/wait-for-it.sh
EXPOSE 8080
CMD ["java", "-jar", "webshop-0.0.1-SNAPSHOT.jar"]
