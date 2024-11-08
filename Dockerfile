FROM openjdk:17-jdk-alpine
EXPOSE 8082
ADD target/tpfoyer-1.0-SNAPSHOT.jar tpfoyer-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","/tpfoyer-1.0-SNAPSHOT.jar"]