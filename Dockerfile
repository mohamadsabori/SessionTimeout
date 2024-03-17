# Use OpenJDK 11 as the base image
FROM openjdk:11

# Set the working directory inside the container
WORKDIR /usr/src/app

# Copy the JAR file from the target directory to the container
COPY target/your-project-name.jar ./app.jar

# Set the entry point command to run the Java application
ENTRYPOINT ["java", "-jar", "app.jar"]
