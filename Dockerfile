FROM maven:3.5.2-jdk-8-alpine AS MAVEN_TOOL_CHAIN
WORKDIR /app
COPY pom.xml /app
COPY src /app/src
RUN mvn clean install

FROM openjdk:8-jre-slim
WORKDIR /app
COPY --from=MAVEN_TOOL_CHAIN /app/target/transaction-service-0.0.1-SNAPSHOT.jar /app
EXPOSE 8082
CMD ["java", "-jar", "transaction-service-0.0.1-SNAPSHOT.jar"]