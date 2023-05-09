# Use the official Maven image as the base image
FROM maven:3.8.4-openjdk-17 as builder

# Set the working directory
WORKDIR /app

# Copy the pom.xml and source code to the container
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -Dmaven.test.skip=true -Dspring.profiles.active=prod -Dspring-boot.build-image=true -Dspring-boot.build-image.imageName=rtms-webservices:latest -DskipTests

# Use the official OpenJDK image as the base image
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/RTMS-webservices-0.0.1-SNAPSHOT.jar /app/rtms-webservices.jar

# Expose the port the application will run on
EXPOSE 8080

# Start the application
CMD ["java", "-jar", "rtms-webservices.jar"]

#Database not setup