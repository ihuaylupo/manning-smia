#! /bin/bash
mvn clean package
docker build -t ostock/authentication-service:0.0.1-SNAPSHOT --build-arg JAR_FILE=target/authentication-service-0.0.1-SNAPSHOT.jar ./authentication-service
docker build -t ostock/configserver:0.0.1-SNAPSHOT --build-arg JAR_FILE=target/configserver-0.0.1-SNAPSHOT.jar ./configserver
docker build -t ostock/eurekaserver:0.0.1-SNAPSHOT --build-arg JAR_FILE=target/eurekaserver-0.0.1-SNAPSHOT.jar ./eurekaserver
docker build -t ostock/gatewayserver:0.0.1-SNAPSHOT --build-arg JAR_FILE=target/gatewayserver-0.0.1-SNAPSHOT.jar ./gatewayserver
docker build -t ostock/licensing-service:0.0.3-SNAPSHOT --build-arg JAR_FILE=target/licensing-service-0.0.3-SNAPSHOT.jar ./licensing-service
docker build -t ostock/organization-service:0.0.1-SNAPSHOT --build-arg JAR_FILE=target/organization-service-0.0.1-SNAPSHOT.jar ./organization-service