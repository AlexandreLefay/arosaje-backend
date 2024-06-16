FROM openjdk:17
COPY target/backend-0.0.1-SNAPSHOT.jar arosaje-backend.jar
ENTRYPOINT ["java","-jar","/arosaje-backend.jar"]

