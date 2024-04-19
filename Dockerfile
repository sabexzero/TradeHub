# Use a base image with JDK and Gradle installed
FROM gradle:latest AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle build files
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle/ ./gradle/

# Copy the application source code
COPY src/ ./src/

# Build the application
RUN ./gradlew build --no-daemon

# Use a lighter base image for the runtime
FROM openjdk:latest

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the build stage to the runtime image
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Specify the command to run the application
CMD ["java", "-jar", "app.jar"]
