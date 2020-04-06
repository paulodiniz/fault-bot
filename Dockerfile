FROM openjdk:8-jdk-alpine
EXPOSE 8080
RUN mkdir -p /app/
ADD build/libs/io.meme.fetcher-1.0-SNAPSHOT.jar /app/io.meme.fetcher-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/io.meme.fetcher-1.0-SNAPSHOT.jar"]
