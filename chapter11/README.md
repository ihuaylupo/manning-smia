## Spring Microservices in Action - Second Edition. Chapter 11

# Introduction
Welcome to Spring Microservices in Action, Chapter 11.  Chapter 11 demonstrates how to use Spring Cloud Sleuth. Spring Cloud Sleuth introduces tracing information into your service calls. The tracing information can be used in conjuction with ELK Stack (Elasticsearch, Logstash, Kibana) to query log messages by one piece of their tracing information (the correlation id).  Also, we will be exploring how to do distributed tracing using Spring Cloud Sleuth and Zipkin.  

1. A Spring Cloud based OAuth2 authentication service that can issue and validate JWT tokens.  
2. A Spring Cloud Config server that is deployed as Docker container and can manage a services configuration information using a file system or GitHub-based repository.
3. A Eureka server running as a Spring-Cloud based service. This service will allow multiple service instances to register with it. Clients that need to call a service will use Eureka to lookup the physical location of the target service.
4. A API Gateway. All of our microservices can be routed through the gateway and have pre, response and post policies enforced on the calls.
5. A organization service that will manage organization data used within Ostock.
6. A licensing service that will manage licensing data used within Ostock.
7. A Postgres SQL database used to hold the data.
8. A Kafka message bus to transport messages between services.
9. A Redis service to act as a distributed cache.
9. A set of ELK Stack applications (Elasticsearch, Logstash, Kibana)
10.A Zipkin Server


## Initial Configuration
1.	Apache Maven (http://maven.apache.org)  All of the code examples in this book have been compiled with Java version 11.
2.	Git Client (http://git-scm.com)
3.  Docker(https://www.docker.com/products/docker-desktop)


## How To Use

To clone and run this application, you'll need [Git](https://git-scm.com), [Maven](https://maven.apache.org/), [Java 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html). From your command line:

```bash
# Clone this repository
$ git clone https://github.com/ihuaylupo/manning-smia

# Go into the repository, by changing to the directory where you have downloaded the 
# Chapter 11 source code and select whether you want the initial or final configuration
$ cd chapter11

# To build the code examples for Chapter 11 as a docker image, open a command-line 
# window and execute the following command:
$ mvn clean package dockerfile:build

# Now we are going to use docker-compose to start the actual image.  To start the docker image, stay in the directory containing  your Chapter 11 source code and  Run the following command: 
$ docker-compose -f docker/docker-compose.yml up
```

# The build command

Will execute the [Spotify dockerfile plugin](https://github.com/spotify/dockerfile-maven) defined in the pom.xml file.  

 Running the above command at the root of the project directory will build all of the projects.  If everything builds successfully you should see a message indicating that the build was successful.

# The Run command

This command will run our services using the docker-compose.yml file located in the /docker directory. 

If everything starts correctly you should see a bunch of Spring Boot information fly by on standard out.  At this point all of the services needed for the chapter code examples will be running.

# Database
You can find the database script as well in the docker directory.

see Remark chapter 5 
 
## Contact

I'd like you to send me an email on <illaryhs@gmail.com> about anything you'd want to say about this software.

### Contributing
Feel free to file an issue if it doesn't work for your code sample. Thanks.