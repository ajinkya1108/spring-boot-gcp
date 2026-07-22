FROM maven:3.9-eclipse-temurin-11

WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Expose application port (change if needed)
EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-jar", "target/mysql-java-connection-1.0.0.jar"]
