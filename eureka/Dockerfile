FROM openjdk:17-alpine
MAINTAINER Jérôme Pagny (pagny.jerome@gmail.com)
ADD ./target/eureka-0.0.1-SNAPSHOT.jar eureka.jar

EXPOSE 8761
ENTRYPOINT ["java", "-jar", "eureka.jar"]