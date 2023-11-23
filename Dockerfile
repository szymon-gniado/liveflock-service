FROM eclipse-temurin:17-jre-ubi9-minimal
LABEL authors="szymon-gniado"

ADD /build/libs/'liveflock-0.0.1-SNAPSHOT.jar' api.jar

ENTRYPOINT ["java", "-jar", "/api.jar"]