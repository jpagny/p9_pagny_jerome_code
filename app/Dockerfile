FROM openjdk:17-alpine
MAINTAINER Jérôme Pagny (pagny.jerome@gmail.com)
ADD ./target/app-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "app.jar"]