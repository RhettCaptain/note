# Sample project for STM 2018

This is a sample project to implement web applications in this STM.

This project is designed to quickly start up development.

Our evaluation is concentrating on business functions and code quality.
Basically you could change everything of this project to fit your need.
You can even use other frameworks if you like.

**But in order to deploy your application successfully, please pay attention on the "Cautions" part.**

## Background knowledge

To develop web applications, you should firstly have basic knowledge about HTML, CSS and JavaScript.

Besides, this sample project uses Spring Framework for server side, you need to check Spring Boot, Spring JDBC Template.

To access relational database, SQL is required.

## Development Environment

Set up [Project Lombok](https://projectlombok.org/) for your IDE (Eclipse or IntelliJ) at first.

Use your IDE and import this project as Maven project.

## Development Mode

This is the default mode, and embedded H2 database will be used.
You can customize its behavior for your need.

But it is generally recommended that you should `NOT` use production database for development.

## Production Mode

At first, copy `src/main/resources/application-production.properties.example` to `src/main/resources/application-production.properties` and fill in your production database information.

But specifying `-Dspring.profiles.active=production` as VM options, such as what the `Procfile` does, the application will start up under production mode.


## Cautions

1. The submitted production binary should be able to kick start using one-line command without too much system requirement setup.
`Procfile` describes how to run the application. You may need to change the jar file name in `Procfile` if you change application name or version.
You can assume that evaluator's environment has preset JDK8, maven, tomcat.
2. You **must** use production database in deployed application.
