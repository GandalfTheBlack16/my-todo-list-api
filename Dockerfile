# Build stage: compile and package with Maven
FROM maven:3.9.4-eclipse-temurin-21-alpine AS builder
WORKDIR /workspace

# copy only pom first for better layer caching
COPY pom.xml .
# if you use a Maven wrapper, copy mvnw and .mvn as well
# COPY mvnw .mvn/ .mvn

COPY src ./src
RUN mvn -B -DskipTests package

# Run stage: smaller runtime image
FROM amazoncorretto:21-alpine3.22
WORKDIR /app

# copy the built jar (adjust pattern if your artifact name differs)
COPY --from=builder /workspace/target/*.jar app.jar

# default port used by Spring Boot; change if needed
EXPOSE 8080

# JVM options can be supplied via JAVA_OPTS env var at runtime
ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]