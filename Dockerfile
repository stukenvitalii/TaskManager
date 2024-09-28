# Stage 1: Base image with PostgreSQL
FROM postgres:16 AS postgres

# Stage 2: Build and run Spring Boot app
FROM openjdk:21 AS builder

# Set working directory
WORKDIR /app

# Copy the application JAR file
COPY ./target/TaskManager-0.0.1-SNAPSHOT.jar TaskManager.jar

# Stage 3: Final image combining PostgreSQL and the Spring Boot app
FROM ubuntu:20.04

# Install PostgreSQL and Java
RUN apt-get update && \
    apt-get install -y postgresql-client openjdk-21-jdk && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Copy JAR from builder stage
COPY --from=builder /app/TaskManager.jar /app/TaskManager.jar

# Set environment variables for Spring Boot app
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/task_manager
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres

# Expose the application port
EXPOSE 8080

# Command to run PostgreSQL and the Spring Boot app
CMD service postgresql start && \
    java -jar /app/TaskManager.jar
