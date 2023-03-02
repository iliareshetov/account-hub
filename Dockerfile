# Use an official Maven 3.8.3 and OpenJDK 17 runtime as a parent image
FROM maven:3.8.3-openjdk-17

# Set the working directory to /app
WORKDIR /app

# Copy the pom.xml and src directories into the container
COPY pom.xml .
COPY src ./src

# Build the application using Maven
RUN mvn package

# Set the application's JAR file as the entry point
ENTRYPOINT ["java", "-jar", "/app/target/account-hub.jar"]
