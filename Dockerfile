# Use an official OpenJDK 17 image as the base
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the compiled test classes into the container
COPY target/test-classes/ .

# Download JUnit Platform Console Launcher JAR
RUN curl -o junit-platform-console-standalone.jar -L https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.8.1/junit-platform-console-standalone-1.8.1.jar

# Specify the command to run your JUnit tests using the JUnit Platform Launcher
CMD ["java", "-cp", ".:/app/*", "org.junit.platform.console.ConsoleLauncher", "--scan-classpath"]
