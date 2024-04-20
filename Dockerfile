FROM gradle:latest AS build

WORKDIR /app

COPY lib/build.gradle /app/
COPY lib/settings.gradle /app/
COPY lib/gradle /app/gradle/
COPY lib/src /app/src/

RUN gradle wrapper
RUN chmod +x gradlew
RUN ./gradlew build

FROM openjdk:latest

WORKDIR /app

COPY --from=build /app/lib/build/libs/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
