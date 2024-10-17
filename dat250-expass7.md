# Expass 7

### Using PostgresQL docker image

I run 
``` docker run -p 8080:5432 \
 -e POSTGRES_USER=postgres \
 -e POSTGRES_PASSWORD=secret \
 -e POSTGRES_DB=mydatabase \
 -d --name my-postgres --rm postgres
 ```


Then I run docker ps to check that it is running:

``` CONTAINER ID   IMAGE      COMMAND                  CREATED          STATUS          PORTS                    NAMES ```

``` bdf412fa323a   postgres   "docker-entrypoint.s…"   18 seconds ago   Up 17 seconds   0.0.0.0:8080->5432/tcp   my-postgres ```

I then run ``` CREATE USER jpa_client WITH PASSWORD 'secret'; ``` using the SQL client DBeaver.

I then add the dependency ``` implementation("org.postgresql:postgresql:42.7.4") ``` to the ``` build.gradle.kts ``` file.

I then replace the old connection parameters (which used H2) in persistance.xml to these lines (the PostgreSQL connection):
``` 
    ...
    <property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQLDialect"/>
    <property name="hibernate.connection.driver_class" value="org.postgresql.Driver"/>
    <property name="hibernate.connection.url" value="jdbc:postgresql://127.0.0.1:5432/postgres"/>
    <property name="hibernate.connection.username" value="jpa_client"/>
    <property name="hibernate.connection.password" value="secret"/>
    ...
```
Then I run my unit tests and I see that they are failing.

I insert ```     <property name="jakarta.persistence.schema-generation.scripts.action" value="drop-and-create"/>
    <property name="jakarta.persistence.schema-generation.scripts.create-target" value="schema.up.sql"/>
    <property name="jakarta.persistence.schema-generation.scripts.drop-target" value="schema.down.sql"/>
    ```
which creates the files schema.up.sql and schema.down.sql.

I tried to execute the schema.up.sql script inside DBeaver, but I keep getting errors that suggest the schema script is somehow trying to create tables that already exist. By skipping those errors I manage to create tables anyway. However the tests are still failing. I don't see what I've done wrong, but I have at least followed every step of the task.

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