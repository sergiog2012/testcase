# test-case

Test Case Backend Developer

### AUTHOR:

Sergio García Isidro <sergio.g.2012@gmail.com>

### NOTES

- This project contains:
    - Test requeriments in pdf
    - Postman collection for testing end-to-end
- Swagger documentation URL: http://localhost:8090/test-case/swagger-ui/index.html
- JSON API doc URL: http://localhost:8090/test-case/v3/api-docs.yaml
- Tests Junit included with full coverage
- Tests containers for MongoDB
- **In order to get the service starting correctly, Docker is needed properly installed.
  This project facilitates a docker-compose file to get a MongoDB container up.**
- Inside "docker" folder in "resources" there is a schema js file needed to populate the MongoDB for initial operations 


### GUIDES

- **Maven install project**: inside the folder project, type

> `mvn clean install`

- **Instructions to run the MongoDB container**: (inside folder "src/main/resources/docker")
> `docker-compose up`

- The container can be up detached too, with flag "-d"
  > `docker-compose up -d`

- Down the container:
  > `docker-compose down`

- **Execute microservice**: (under root project folder)

> `mvn spring-boot:run`

- **Execute microservice**: (under "target" folder)

> `java -jar test-0.0.1-SNAPSHOT.jar`



