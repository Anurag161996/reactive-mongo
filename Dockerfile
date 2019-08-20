FROM openjdk:8-jdk-alpine
ADD build/libs/com.tech.reactive-0.1.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
