FROM openjdk:17
ADD target/tp-foyer-5.0.0.jar foyer.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "foyer.jar"]