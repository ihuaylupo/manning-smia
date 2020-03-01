FROM openjdk:11-slim
MAINTAINER "tinhcao <caodangtinh@gmail.com>"
WORKDIR /app
COPY ./target/licensing-service.jar ./
EXPOSE 8080
CMD ["java", "-jar", "licensing-service.jar"]