# simple-spring-rest-service

develop:
[![Build Status](https://travis-ci.org/Cameron-C-Chapman/simple-spring-rest-service.svg?branch=develop)](https://travis-ci.org/Cameron-C-Chapman/simple-spring-rest-service)
[![codecov](https://codecov.io/gh/Cameron-C-Chapman/simple-spring-rest-service/branch/develop/graph/badge.svg)](https://codecov.io/gh/Cameron-C-Chapman/simple-spring-rest-service)
<br/>
master:
[![Build Status](https://travis-ci.org/Cameron-C-Chapman/simple-spring-rest-service.svg?branch=master)](https://travis-ci.org/Cameron-C-Chapman/simple-spring-rest-service)
[![codecov](https://codecov.io/gh/Cameron-C-Chapman/simple-spring-rest-service/branch/master/graph/badge.svg)](https://codecov.io/gh/Cameron-C-Chapman/simple-spring-rest-service)

### About
This repo is an example of a simple spring rest service that demonstrates some good practices (in my opinion) of how to structure a spring boot rest service and how to integrate unit testing, continuous integration, and code coverage reporting.

### Running the Application
```
./gradlew clean assemble
./gradlew bootRun -Dspring.profiles.active=local
```

### Testing the Application
* Through swagger-ui
  * With the application running navigate to [http://localhost:9000/swagger-ui.html](http://localhost:9000/swagger-ui.html)
  * You will see several controllers exposed including the Customer Controller which exposes the functionality for viewing all customers, viewing a specific customer, and adding a customer.
  * For more information on swagger visit [http://swagger.io/](http://swagger.io/)
* Through Postman
  * Importing the Postman collection into Postman and testing the pre-configured calls.
  ```
  /resources/postman/simpleSpringRestService.postman_collection.json
  ```
  * For more information on Postman visit [https://www.getpostman.com/docs/](https://www.getpostman.com/docs/)
* Through Advanced Rest Client
  * Importing the Advanced Rest Client project into Advanced Rest Client and testing the pre-configured calls.
  ```
  /resources/advanced-rest-client/simpleSpringRestService.json
  ```
  * For more information on Advanced Rest Client visit [https://advancedrestclient.com/](https://advancedrestclient.com/)

### Some Things Not Included With This Demo
* Authentication
  * How authentication needs to be setup is pretty dependent on the way it plans to be used within the environment it is being deployed and the systems it needs to interact with so I left it out of this demo.

### Some Things Included With This Demo
* Continuous Integration
  * Continuous integration is configured through TravisCI. For more information on TravisCI visit [https://docs.travis-ci.com/](https://docs.travis-ci.com/)
* Code Coverage Analysis
  * Code coverage analysis is configured through codecov.io. For more information on codecov.io visit [https://codecov.io/](https://codecov.io/)
* Interactive documentation through swagger-ui and pre-configured test suites through Postman and Advanced Rest Client.