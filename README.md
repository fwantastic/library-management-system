# library-management-system
REST APIs for Library Management System

## Features
- CRUD for book entities

## Architecture
Utilizes Dynamodb Local to store data. 

## Local Development
IDE
- Download [Intellij community edition](https://www.jetbrains.com/idea/download/#section=mac)
- Copy [intellij-java-google-style.xml](https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml) to a local folder
- Apply the stylesheet by Intellij Preferences -> Editor -> Code Style -> Import

Docker
- Download [Docker Desktop](https://www.docker.com/)

Dynamodb Local
- Download [Dynamodb Local](https://hub.docker.com/r/amazon/dynamodb-local/) image

### How to run
- Start Dynamodb Local docker image with Host Port `8000`
- Start Spring Boot application by running the main method in `LibraryManagementApplication` class
- Use Postman collections to test APIs

references
- https://github1s.com/anicetkeric/spring-boot-aws-dynamodb/blob/main/src/main/java/com/sample/microservice/awsdynamodb/domain/Employee.java


list of topics
- twitter
- spotify


TODOs
- create dto
  - controller converts dto to model
  - mapper that handles conversion
- create async service
  - check dynamodb supports async
- add field validation
  - title less than 100 chars
  - rating in between 1-5
  - author is not null
- add support for pagination
- use cache
  - check if in memory cache is worth
- add more features for other entities
  - author/user
  - publisher
  - utilize single dynamodb table