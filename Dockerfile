FROM openjdk:17-jdk-alpine

# Expose le port 8082 pour l'application
EXPOSE 8082

# Ajoute le fichier JAR à l'image Docker
ADD target/tpfoyer-1.0.jar tpfoyer-1.0.jar

# Exécute l'application Java
ENTRYPOINT ["java", "-jar", "/tpfoyer-1.0.jar"]
