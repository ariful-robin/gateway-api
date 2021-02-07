# gateway-api
This is a REST API project for gateway management. This project includes REST APIs for managing CRUD 
operations of gateways and devices under those gateways. Number of constraints have been set while 
developing these APIs. Followings are few constraints induced here.

- Attributes of gateway and device need to be validated if mentioned
- No of child devices under a gateway must not exceed max capacity of 10

Note: Context root of this application is /api

# Framework/Library Used

- openjdk12
- Spring Boot v2.4.2
- lombok
- junit
- hamcrest
- mockito 
- swagger (springfox-swagger)
- docker

# Database
H2 in memory database has been used. Application will load some test data while starting up.
H2 console is also configured to visualize database however the data are volatile by nature.

- H2 console url: http://localhost:8080/api/h2-console/

# Test Cases
Number of test cases are included in this application to test web mvc as well as to test the constraints
with unit testing.

# Build and Run as Executable JAR
### Build
Maven is used as build and dependency management tools. Using maven application can be built to produce
an executable jar.

- Go to the project root directory
- Build command: mvn clean package
- An executable jar is produced inside /target sub-folder

### Run
Application can be run as executable jar with following steps.

- Follow Build section above to build
- Install jre >= 12
- Run command: _java -jar target/gateways-0.0.1-SNAPSHOT.jar_ [Here, jar file is from build]

# Build and Run with Docker

### Build
- Install docker (if you don't have it in machine)
- Go the project root containing the Dockerfile
- Build command: _docker build -t gateway ._

### Run
- Run command: _docker run -p 8080:8080 -d_ 

# API Documentation
swagger has been incorporated here to produce API documentation. You can access api documentation after
running the application with swagger ui.
- swagger ui: http://localhost:8080/api/swagger-ui/

# API Client
Postman was used as REST client to test the APIs. Please refer to the postman collection. 
- musala-gateway.postman_collection.json







