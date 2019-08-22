FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/plane-consumer-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} plane-consumer-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/plane-consumer-0.0.1-SNAPSHOT.jar"]