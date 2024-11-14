# Use a specific version of OpenJDK 17 as the base image
FROM openjdk:17-jdk-alpine

# Expose port 8082 for your Spring Boot application
EXPOSE 8082

# Add the built JAR file to the container's file system
ADD target/tpfoyer-1.0.jar tpfoyer-1.0.jar  # Utiliser la version stable du JAR

# Command to run the JAR file when the container starts
ENTRYPOINT ["java", "-jar", "/tpfoyer-1.0.jar"]  # Utiliser la version stable du JAR
