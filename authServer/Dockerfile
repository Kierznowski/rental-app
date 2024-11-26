FROM amazoncorretto:17-alpine-jdk

WORKDIR /app

COPY target/authServer-0.0.1-SNAPSHOT.jar /app/authServer.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/authServer.jar"]

