FROM openjdk:11
RUN mkdir /app
WORKDIR /app
COPY javaskills-homework.jar /app/javaskills-homework.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/javaskills-homework.jar"]