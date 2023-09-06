# Use a specific version and a slimmer image for the build stage
FROM openjdk:20-jdk-slim as build

# Set working directory
WORKDIR /app

# Copy Maven configuration and executable wrapper
COPY .mvn .mvn
COPY mvnw .

COPY pom.xml .
RUN ./mvnw dependency:go-offline

COPY src src
RUN ./mvnw -B package

FROM openjdk:20-jdk-slim

WORKDIR /app

COPY --from=build /app/target/geektext-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "geektext-0.0.1-SNAPSHOT.jar"]
