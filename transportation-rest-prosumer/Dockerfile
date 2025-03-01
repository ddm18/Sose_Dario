FROM openjdk:17-jdk-slim

WORKDIR /app

ARG JAR_FILE=target/transportation-rest-prosumer-0.1.0-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
