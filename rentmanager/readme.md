# RentManager

Spring MVC project running scooter sharing service.

### Goals

- Learn Spring enterprise stack technologies.

### Project modules structure

- config is responsible for DI container configuration;
- controller module obtains controllers beans;
- dao module provides persistance layer abstraction, uses JPA;
- domain contains busieness entities and enums;
- security module provides authentication and authorization;
- service module operates on business logic of application;
- web module crowns whole application and package it as .war file.

### Requirements to run application:

- JDK 11 or higher;
- Apache TomCat 9.0.33;
- Maven;
- MySQL 5.7 or higher;
- FlyWay for initialising db and populating schema;
- See full list of dependencies in pom.xml;

### Steps to run and deploy application:

- Install JDK;
- Compile app code using maven CLI or IDE;
- Install MySQL;
- Create schema 'test';
- Run FlyWay using CLI or IDE plugin;
- Deploy your .WAR file into TomCat using CLI or IDE plugin;
- Send REST requests to the server.

### Additional info

Intellij IDEA's generated javadoc holds inside module domain/documentation folder.
