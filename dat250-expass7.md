# Expass 7

### Using PostgresQL docker image

After a long period of troubleshooting I was able to figure out the bug! Turns out that redownloading the gradle zip file found in gradle-wrapper.properties and replacing the old files solved it. I will have it done by tomorrow (thursday) morning.


### Creating my own docker image:

For my [Dockerfile](https://github.com/Kremtastic/DAT250-Expass/blob/main/demo/Dockerfile) I built it based on the official gradle image.
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

I built the application with the cmd: ```docker build -t my-spring-boot-app . ```.

Then I run it with: ``` docker run -p 8080:8080 my-spring-boot-app ```