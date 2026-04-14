FROM eclipse-temurin:25-jdk AS build
WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY settings.gradle .

COPY backend/build.gradle backend/
COPY backend/src backend/src

RUN chmod +x gradlew

RUN ./gradlew :backend:bootJar -x test

FROM eclipse-temurin:25-jre
WORKDIR /app

RUN groupadd -r spring && useradd -r -g spring spring
USER spring

COPY --from=build --chown=spring:spring /app/backend/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
