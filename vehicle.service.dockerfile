FROM openjdk:8-alpine
RUN apk update && apk add bash
RUN mkdir -p /opt/app
ENV PROJECT_HOME /opt/app
COPY ./target/vehicle-0.0.1-SNAPSHOT.jar $PROJECT_HOME/vehicle.jar
WORKDIR $PROJECT_HOME
CMD ["java", "-jar", "-Dspring.profiles.active=prod" ,"./vehicle.jar"]