FROM gradle:jdk8 as build
COPY . /project/
WORKDIR /project
RUN gradle build

FROM openjdk:8-jdk-alpine
RUN mkdir /app
COPY --from=build /project/build/libs/*.jar /app/registry-service.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","/app/registry-service.jar"]
