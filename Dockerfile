# Stage 1: Base image with PostgreSQL and JDK installation
FROM postgres:16 AS base

# Update repositories and install OpenJDK 21
RUN apt-get update && \
    apt-get install -y openjdk-21-jdk --no-install-recommends && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Set JAVA_HOME environment variable
ENV JAVA_HOME /usr/lib/jvm/java-21-openjdk-amd64
ENV PATH $JAVA_HOME/bin:$PATH

# Stage 2: Build and run Spring Boot app
FROM openjdk:21 AS builder

LABEL authors="stukenvitalii"

# Copy the application JAR file
COPY ./target/TaskManager-0.0.1-SNAPSHOT.jar TaskManager.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/TaskManager.jar"]
