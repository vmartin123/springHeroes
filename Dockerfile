FROM openjdk:8-jre-alpine
COPY ./target/heroes-0.0.1-SNAPSHOT.jar /usr/src/heroes/
WORKDIR /usr/src/heroes
EXPOSE 8080
CMD ["java", "-jar", "heroes-0.0.1-SNAPSHOT.jar"]
