FROM openjdk:17-alpine
MAINTAINER Jérôme Pagny (pagny.jerome@gmail.com)
ADD ./target/diabetesAssessment-0.0.1-SNAPSHOT.jar diabetesAssessment-api.jar
EXPOSE 9003
ENTRYPOINT ["java", "-jar", "diabetesAssessment-api.jar"]