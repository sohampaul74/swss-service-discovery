FROM openjdk:8-jdk-alpine
MAINTAINER org.swss
COPY target/service-discovery-0.0.1-SNAPSHOT.jar docker-service-discovery-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/docker-service-discovery-0.0.1-SNAPSHOT.jar"] 
