FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/bands-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "bands-0.0.1-SNAPSHOT.jar"]