# AutoScout24. Kendy Alvarado backend applicant test
The goal of this service is to test the development skills of **Kendy Alvarado**. Developing a small service that is capable to upload listing and contact data form csv and do basic reports over the imported data.

## General considerations
- Documentation: the main services are intradocumented (Javadocs).
  -No hard coded values. All the config properties are defined in the `application.properties` file
- Test coverage: the main business services were tested to validate their proper functionality.
- The data is stored in memory, that means that every time that the service is stopped all the data will be deleted.

## Service Architecture
- Data Transfer Objects (DTO): Objects to be used for outside communication via the API
- Controller: Implements the processing logic of the web service, parsing of parameters and validation of in and outputs.
- Service: Implements the business logic and handles the access to the Entity Objects.
- Entity Objects: Interface for the database. Inserts, updates, deletes and reads objects from the database.
- Repository Objects: Functional Objects to be stored in the database.

### Technologies used:
- Java 11
- Spring Boot
- Database H2 (In-Memory)
- Gradle


### Running the web services
- To get access to the API, you should run on the terminal
  `./gradlew bootRun`
- which starts a webserver on port 8080 (http://localhost:8080) and serves SwaggerUI where can inspect and try existing endpoints.

### Running test from command line
- To run test, you should run on the terminal
    `./gradlew test`
