FROM eclipse-temurin:21-jre-alpine
VOLUME /tmp
EXPOSE 8080
ADD ./build/libs/rental-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]