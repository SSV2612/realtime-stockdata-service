# Step 1: Use an official Java runtime image as the base image
FROM openjdk:17-jdk-slim

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the application's JAR file into the container
COPY target/RealTimeTradingSystem-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose the port on which the app runs (Spring Boot default port is 8080)
EXPOSE 8080

# Step 5: Command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]