FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/producer-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} producer-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/producer-0.0.1-SNAPSHOT.jar"]