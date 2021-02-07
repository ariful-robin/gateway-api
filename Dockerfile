FROM maven:3.6.3-openjdk
WORKDIR /app
COPY pom.xml .
COPY src src/
RUN mvn clean package