FROM openjdk:17-jdk
ARG JAR_FILE=build/libs/*.jar
COPY ./build/libs/casino-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
