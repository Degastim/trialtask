FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/trialtask-1.0.0.jar ./trialtask.jar
EXPOSE 8081:8081
CMD ["java", "-jar", "/app/trialtask.jar"]