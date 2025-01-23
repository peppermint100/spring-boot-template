FROM amazoncorretto:17.0.10-alpine3.19 AS builder
WORKDIR /build
COPY . .
RUN ./gradlew clean bootJar

FROM amazoncorretto:17.0.10-alpine3.19
WORKDIR /app
COPY --from=builder /build/build/libs/app.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]