FROM eclipse-temurin:21-jre-alpine

# Set a non-root user for security
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY target/accounts-0.0.1-SNAPSHOT.jar app.jar

# Adjust JVM options for better performance in containers
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:+UseG1GC"

# Expose the port the application will run on
EXPOSE 8080

# Switch to the non-root user
USER appuser

# Command to run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

