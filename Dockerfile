FROM eclipse-temurin:21-jdk as build
COPY . .
RUN mvn clean package -DskipTests

FROM amazoncorretto:latest
COPY --from=build /target/RockAPI-0.0.1-SNAPSHOT.jar RockAPI.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","RockAPI.jar"]

