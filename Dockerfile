FROM openjdk:8
COPY target/rest-api-0.0.1-SNAPSHOT.jar rest-api-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "rest-api-0.0.1-SNAPSHOT.jar"]
