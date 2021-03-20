FROM gradle:jdk8 as build
COPY . /project/
WORKDIR /project
RUN gradle build

FROM openjdk:8-jdk-alpine
RUN mkdir /app
COPY --from=build /project/build/libs/*.jar /app/gateway-service.jar
EXPOSE 8765
ENTRYPOINT ["java","-jar","/app/gateway-service.jar"]
