FROM maven:3.8.6-jdk-11-slim AS builder
WORKDIR /tmp/shopgular-backend/
COPY . /tmp/shopgular-backend/
RUN mvn clean install -DskipTests

FROM openjdk:8-jdk-alpine
COPY --from=builder /tmp/shopgular-backend/target/shopgular-1.0.0.jar .
ENTRYPOINT ["java", "-jar", "/shopgular-1.0.0.jar"]
