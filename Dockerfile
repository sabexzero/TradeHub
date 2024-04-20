FROM gradle:latest AS build

WORKDIR /app

COPY build.gradle .
COPY settings.gradle .
COPY gradlew .sss
COPY .gradle ./gradle/

COPY src ./src/

RUN apt-get update && \
    apt-get install dos2unix && \
    apt-get clean \

RUN sudo dos2unix gradlew
RUN sudo chmod 777 gradlew
RUN sudo ./gradlew lib:build

FROM openjdk:latest

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]