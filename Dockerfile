# syntax=docker/dockerfile:1

FROM openjdk:17-jdk-slim-buster
VOLUME /tmp
COPY target/Ta3ikDataBase-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]