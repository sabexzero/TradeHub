FROM openjdk:11
WORKDIR /my-project
COPY . .
RUN ./gradlew clean bootJar
ENTRYPOINT ["java", "-jar", "build/libs/*.jar"]