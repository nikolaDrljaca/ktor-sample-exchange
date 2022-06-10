FROM gradle:7-jdk11-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle shadowJar --no-daemon

FROM amazoncorretto:11-alpine3.13
EXPOSE 8081:8081
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/ktor-generator.jar
ENTRYPOINT ["java","-jar","/app/ktor-generator.jar"]