# Sectors service

Sectors service is a service that provides one endpoint for creating sectors.

## Requirements

* [OpenJDK 17](https://openjdk.java.net/projects/jdk/17/)

## Run application

This project can be run from docker. For this, clone this project, open the terminal in your computer, go to the folder 
of this project and run docker-compose up. After successful application start, open the app from your browser from
http://localhost:8080/  and start using the app.

This application uses h2 database. It is started automatically when the application is started. It is accessed via
browser at http://localhost:8080/h2-console. Username and password are in application.properties file.

Project can also be run directly
from [SectorsApplication.main()](src/main/java/sectors/sectors/SectorsApplication.java)
method or via executable jar file. More info on running Spring applications:
[running your application.](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html)


