FROM openjdk:8-jdk

COPY ./ /app
WORKDIR /app

CMD ["./mvnw", "spring-boot:run"]