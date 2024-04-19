FROM openjdk:11
WORKDIR /my-project
CMD ["./gradlew", "clean", "bootJar"]
COPY build/libs/*.jar app.jar
