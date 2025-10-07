FROM maven:3.9-eclipse-temurin-22-alpine AS dev

WORKDIR /app
COPY . /app

CMD ["mvn", "spring-boot:run"]

