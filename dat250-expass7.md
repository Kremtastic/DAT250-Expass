# Expass 7

### Using PostgresQL docker image

I was unable to run the tests because of an error I experienced in back in expass4.




### Creating my own docker image:

For my Dockerfile I built it based on the official gradle image.
Then I used a lighter JDK image for running the application.
Here is the contents of the Dockerfile:
```
# Stage 1: Build the application using Gradle
FROM gradle:8-jdk21 AS builder
WORKDIR /app
COPY --chown=gradle:gradle . /app
RUN gradle bootJar

# Stage 2: Use a lighter JDK image for running the application
FROM eclipse-temurin:21-jdk AS runtime
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/app.jar"]
```