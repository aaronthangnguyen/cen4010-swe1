# Use Maven image for the build stage
FROM maven:3.9-eclipse-temurin-20 as build
WORKDIR /app

# Copy POM and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and compile the project
COPY src src
RUN mvn -B package

# Use OpenJDK image for the runtime stage
FROM openjdk:20-jdk-slim
WORKDIR /app

# Copy the compiled jar from the build stage
COPY --from=build /app/target/geektext-*.jar geektext-app.jar

# Expose port 8080 for the application
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "geektext-app.jar"]
