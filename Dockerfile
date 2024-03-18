# Use OpenJDK 11 as the base image
FROM openjdk:11

# Set the working directory inside the container
WORKDIR /usr/src/app

# Copy the JAR file from the target directory to the container
COPY target/sessionTimeout-1.0-SNAPSHOT.jar ./sessionTimeout-1.0-SNAPSHOT.jar

# Set the entry point command to run the Java application
ENTRYPOINT ["java", "-jar", "sessionTimeout-1.0-SNAPSHOT.jar"]
