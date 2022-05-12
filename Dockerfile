FROM adoptopenjdk/openjdk14
EXPOSE 8080
ADD target/*.jar flight-control-api-0.0.1-SNAPSHOT.jar.jar
ENTRYPOINT ["java", "-jar", "flight-control-api-0.0.1-SNAPSHOT.jar.jar"]