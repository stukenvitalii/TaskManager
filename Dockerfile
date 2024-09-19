FROM openjdk:21
LABEL authors="stukenvitalii"

COPY ./target/TaskManager-0.0.1-SNAPSHOT.jar TaskManager.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/TaskManager-0.0.1-SNAPSHOT.jar"]