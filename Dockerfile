FROM eclipse-temurin:17 AS BUILD_IMAGE
RUN mkdir -p /build
WORKDIR /sectors
COPY . /sectors/
RUN ./gradlew build

FROM eclipse-temurin:17-jre-alpine
COPY --from=BUILD_IMAGE /sectors/build/libs/sectors-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java","-jar","sectors-0.0.1-SNAPSHOT.jar"]