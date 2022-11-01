FROM openjdk:17-alpine
MAINTAINER Jérôme Pagny (pagny.jerome@gmail.com)
ADD ./target/patient-0.0.1-SNAPSHOT.jar patient-api.jar
EXPOSE 9001
ENTRYPOINT ["java", "-jar", "patient-api.jar"]
