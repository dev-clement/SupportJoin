# ---- Build Stage ----
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests

# Explanation: This is a docker stage, it's kind of a clean place where eveyrthing could
# be run on stand-alone
# ---- Runtime Stage ----
FROM eclipse-temurin:21-jdk
WORKDIR /app

# In your Spring Boot Dockerfile
RUN apt-get update && apt-get install -y mariadb-client

# This copy the app.jar from the first stage to put it in this stage
COPY --from=builder /app/target/*.jar app.jar

# Copy the wait-for-mariadb script
COPY ./wait-for-mariadb.sh /tmp/wait-for-mariadb.sh
RUN sed -i 's/\r$//' /tmp/wait-for-mariadb.sh && chmod +x /tmp/wait-for-mariadb.sh 

# Default command (but will be overridden by entrypoint.sh in compose)
CMD ["java", "-jar", "app.jar"]
