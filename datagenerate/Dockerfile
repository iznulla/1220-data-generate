#FROM vegardit/graalvm-maven:latest-java17 as builder
#WORKDIR /app
#COPY . /app/.
#RUN mvn -f /app/pom.xml clean package -DskipTests


FROM openjdk:17-oracle
WORKDIR /app

COPY ./target/*.jar /app/*.jar
COPY ./.env /app/.env

EXPOSE 8181
ENTRYPOINT ["java", "-jar", "/app/*.jar"]
