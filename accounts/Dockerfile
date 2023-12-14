#start with base image containing the java runtime.
FROM openjdk:17-jdk-slim

#who is maintaing this docker image
MAINTAINER pponali

#add the application jar to the docker image.
COPY target/account-service-0.0.1-SNAPSHOT.jar account-service-0.0.1-SNAPSHOT.jar

#while generate container from docker image, please execute the so and so commands
ENTRYPOINT ["java", "-jar", "account-service-0.0.1-SNAPSHOT.jar"]


