FROM openjdk:11-jre

COPY drone-processing/target/scala-2.13/drone-processing.jar /app/application.jar
RUN
CMD ["java", "-jar", "/app/application.jar"]