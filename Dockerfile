FROM maven:3.9-eclipse-temurin-11 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package

# Runtime stage
FROM eclipse-temurin:11-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/mysql-java-connection-1.0.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
