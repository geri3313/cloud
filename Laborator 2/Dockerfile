FROM eclipse-temurin:19-alpine
VOLUME /tmp
EXPOSE 8080
WORKDIR /backend
ADD Laborator\ 2/target/spring-boot-rest-api-postgresql-0.0.1-SNAPSHOT.jar fti.jar
ENTRYPOINT ["java","-jar","fti.jar"]
