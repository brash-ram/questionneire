
# AS <NAME> to name this stage as maven
FROM maven:3.6.3 AS maven
#LABEL MAINTAINER="sgwebfreelancer@gmail.com"

WORKDIR /usr/src/app
COPY . /usr/src/app
# Compile and package the application to an executable JAR
RUN mvn package

# For Java 11,
FROM optopenjdk/openjdk17:alpine-jre

ARG JAR_FILE=spring-boot-api-tutorial.jar

WORKDIR /opt/app

# Copy the spring-boot-api-tutorial.jar from the maven stage to the /opt/app directory of the current stage.
COPY --from=maven /usr/src/app/target/*.jar /opt/app/app.jar

ENTRYPOINT ["java","-jar","app.jar"]