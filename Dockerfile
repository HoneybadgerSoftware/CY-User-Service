FROM amazoncorretto:17.0.0-alpine
VOLUME /tmp
WORKDIR /app
COPY build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]