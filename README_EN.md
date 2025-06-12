# TODO REST API – Practical #7.2 (Difficulty: 5 s.p.)

This repository contains a Spring Boot-based CRUD REST API for a TODO-list application, designed for fast deployment and usage on AWS Elastic Beanstalk.

## Project Overview

Purpose: Build a rapidly deployable REST API that supports task state management, user roles, multilingual support, and automated deployment.

## Features

* CRUD API for TODO tasks
* Task fields: title, description, due date, and status
* State machine logic with allowed transitions:

  * Initial: Planned
  * Transition to: Work\_in\_progress, Postponed
  * Then: Notified, Signed
  * Final: Done
  * Cancelled can occur from any state and is final
* State transition logic fully unit tested

## Entities

* User: basic user entity with authentication/authorization
* Role: separate entity; users can be assigned roles
* Task: the core TODO object

## Security

* Spring Security with Basic Auth, JWT authentication, and OAuth2 (tested)
* Role-based access control
* Role management via a separate controller

## AOP Logging

* Applied Spring AOP for logging controller and service method calls

## i18n Support

* API supports internationalization (i18n)
* Language selected via request header or query parameter
* Localized error messages via message bundles

## Error Handling

* Custom exceptions
* Centralized error handling using @ControllerAdvice

## Documentation

* Integrated Swagger/OpenAPI 3 UI
* Currently supports Basic Auth authentication in Swagger
* Available at /swagger-ui.html

## Storage Options

* In-memory (H2)
* PostgreSQL
* MariaDB

## Deployment

* App packaged as executable JAR
* Deployed to AWS Elastic Beanstalk
* GitHub Actions used for CI/CD (beanstalk-deploy action)

### Resources

* [https://aws.plainenglish.io/deploy-spring-boot-applications-into-aws-using-aws-elastic-beanstalk-b2204d8fbd9c](https://aws.plainenglish.io/deploy-spring-boot-applications-into-aws-using-aws-elastic-beanstalk-b2204d8fbd9c)
* [https://github.com/marketplace/actions/beanstalk-deploy](https://github.com/marketplace/actions/beanstalk-deploy)

## Shell Scripts

* A set of curl-based shell scripts provided for basic API usage:

  * Create user / assign roles
  * Add TODO task
  * Update task status
  * Query localized task list

## Project Structure

```
├── ...todo_list
│ ├── aop # Logging with Spring AOP
│ ├── config # App, locale, and security configuration
│ ├── controllers # REST controllers (Task, User, Role)
│ ├── dto # Data transfer objects
│ ├── exceptions # Custom exceptions 
│ ├── models # JPA entities (Task, User, Role)
│ ├── projections # Optional: interface-based DTO projections
│ ├── repository # Spring Data repositories
│ ├── security # Security configuration and filters
│ ├── service # Business logic
│ └── TodoListApplication.java # Main Spring Boot entry point
├── resources
│ ├── static # (Optional) static assets
│ ├── templates # (Optional) view templates if using Thymeleaf
│ ├── application.yaml # Main application config
│ ├── messages.properties # Default i18n messages
│ ├── messages_en.properties # English messages
│ └── messages_uk.properties # Ukrainian messages
└── test
```

## Summary

This TODO REST API project demonstrates advanced usage of:

* State machines
* Spring Security (Basic Auth, JWT, OAuth2 tested)
* Multilingual REST API
* Centralized exception handling
* AOP-based logging
* AWS deployment via Elastic Beanstalk
* CI/CD pipelines
* REST documentation via Swagger

Ready for real-world production deployments and team-based collaboration.
