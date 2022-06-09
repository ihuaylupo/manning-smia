#! /bin/bash
mvn clean package
docker build -t ostock/authentication-service:chapter12 --build-arg JAR_FILE=target/authentication-service-chapter12.jar ./authentication-service
docker build -t ostock/configserver:chapter12 --build-arg JAR_FILE=target/configserver-chapter12.jar ./configserver
docker build -t ostock/eurekaserver:chapter12 --build-arg JAR_FILE=target/eurekaserver-chapter12.jar ./eurekaserver
docker build -t ostock/gatewayserver:chapter12 --build-arg JAR_FILE=target/gatewayserver-chapter12.jar ./gatewayserver
docker build -t ostock/licensing-service:chapter12 --build-arg JAR_FILE=target/licensing-service-chapter12.jar ./licensing-service
docker build -t ostock/organization-service:chapter12 --build-arg JAR_FILE=target/organization-service-chapter12.jar ./organization-service