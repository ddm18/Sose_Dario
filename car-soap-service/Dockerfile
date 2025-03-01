FROM openjdk:17-jdk-slim

WORKDIR /app

ARG JAR_FILE=target/car-soap-service-0.1.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
