FROM openjdk:21
VOLUME /tmp
ARG JAR_FILE=build/libs/aaa-simple-webapp.jar
COPY ${JAR_FILE} aaa-simple-webapp.jar
ENTRYPOINT ["java","-jar","/aaa-simple-webapp.jar"]
