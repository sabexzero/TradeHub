FROM gradle:latest AS build

WORKDIR /app

COPY build.gradle .
COPY settings.gradle .
COPY gradle ./gradle/

COPY src ./src/


RUN gradle wrapper
RUN chmod +x gradlew
RUN ./gradlew build

FROM openjdk:latest

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]