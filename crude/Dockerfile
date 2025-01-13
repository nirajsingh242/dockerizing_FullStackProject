# Use official OpenJDK base image for Java
# Compilation
FROM openjdk:17-jdk-slim AS builder

# Set the working directory
WORKDIR /app

# Copy the backend project (including mvnw) into the container
COPY . /app

# Build the app (assuming you're using Maven here)
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests
RUN echo "Listing files in /app" && ls -l /app

# Run
# Use a minimal base image for running the app
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built jar from the builder stage
COPY --from=builder /app/target/*.jar /app/app.jar

# Copy the 'wait-for-it.sh' script from the builder stage into the final image
COPY --from=builder /app/scripts/wait-for-it.sh /scripts/wait-for-it.sh


# Make sure the 'wait-for-it.sh' script is executable
RUN chmod +x /scripts/wait-for-it.sh

# Expose the application port
EXPOSE 8080
RUN ls -l /app
# Run the Spring Boot application
ENTRYPOINT ["/scripts/wait-for-it.sh", "postgres:5432", "--", "java", "-jar", "/app/app.jar"]
#ENTRYPOINT ["jar","-tf", "/app/app.jar"]


