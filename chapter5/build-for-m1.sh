#! /bin/bash
mvn clean package
docker build -t ostock/configserver:0.0.1-SNAPSHOT --build-arg JAR_FILE=target/configserver-0.0.1-SNAPSHOT.jar ./configserver
docker build -t ostock/licensing-service:0.0.2-SNAPSHOT --build-arg JAR_FILE=target/licensing-service-0.0.2-SNAPSHOT.jar ./licensing-service