FROM maven:3.8-jdk-11 as build
COPY . .
RUN mvn clean install


FROM openjdk:11-jre-slim
COPY --from=build ./target/*.jar /usr/app/dexa.jar
ENTRYPOINT ["java","-jar","/usr/app/dexa.jar"]
