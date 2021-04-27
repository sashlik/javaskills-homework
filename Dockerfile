FROM openjdk:11
RUN mkdir /app
WORKDIR /app
COPY target/javaskills-homework.jar /app/javaskills-homework.jar
EXPOSE 9081
ENTRYPOINT ["java", "-jar", "/app/javaskills-homework.jar"]