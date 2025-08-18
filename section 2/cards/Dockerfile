# Use a lightweight base image with OpenJDK 21
FROM eclipse-temurin:21-jre-alpine

# Set a non-root user for security
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file to the container
COPY target/cards-0.0.1-SNAPSHOT.jar app.jar

# Adjust JVM options for better performance in containers
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:+UseG1GC"
#ENV CONFIG_SERVER_URL="configserver:http://myuser:mypassword@host.docker.internal:8071/"

# Expose the port the application will run on
EXPOSE 8090

# Switch to the non-root user
USER appuser

# Command to run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]