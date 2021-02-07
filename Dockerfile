FROM maven:3.6.3-openjdk as build
WORKDIR /app
COPY pom.xml .
COPY src src/
RUN mvn clean package

FROM openjdk:12
ARG server_port=8080
EXPOSE $server_port
COPY --from=build /app/target/*.jar gateway-api.jar
ENTRYPOINT ["java", "-jar", "gateway-api.jar"]