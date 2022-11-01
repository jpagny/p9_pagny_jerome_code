FROM openjdk:17-alpine
MAINTAINER Jérôme Pagny (pagny.jerome@gmail.com)
ADD ./target/note-0.0.1-SNAPSHOT.jar note-api.jar

EXPOSE 9002
ENTRYPOINT ["java", "-jar", "note-api.jar"]