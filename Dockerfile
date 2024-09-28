# Stage 1: Base image with PostgreSQL and Liquibase
FROM postgres:16 as base
ENV POSTGRES_DB=task_manager
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres

# Copy your Liquibase migration files
COPY ./migrations /liquibase/changelog

# Run PostgreSQL service in the background
RUN apt-get update && apt-get install -y openjdk-21-jdk

# Stage 2: Build and run Spring Boot app with Liquibase and PostgreSQL
FROM openjdk:21-jdk-slim as builder
LABEL authors="stukenvitalii"

# Copy the JAR file
COPY ./target/TaskManager-0.0.1-SNAPSHOT.jar /TaskManager.jar

# Copy the PostgreSQL data and migrations from the previous stage
COPY --from=base /var/lib/postgresql/data /var/lib/postgresql/data
COPY --from=base /liquibase/changelog /liquibase/changelog

# Liquibase execution before running the app
RUN java -jar /TaskManager.jar && \
    liquibase --changelog-file=/liquibase/changelog/master.xml \
    --driver=org.postgresql.Driver \
    --url=jdbc:postgresql://localhost:5432/task_manager \
    --username=postgres \
    --password=postgres update

EXPOSE 8080

# Entry point to run your Spring Boot app
ENTRYPOINT ["java", "-jar", "/TaskManager.jar"]
