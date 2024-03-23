FROM eclipse-temurin:21-jdk-alpine

WORKDIR /alurex

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw clean package -DskipTests

RUN cp /alurex/target/*.jar alurex.jar

ENTRYPOINT ["java", "-jar", "alurex.jar"]



