# PRUEBA PRECIOS TEXT - Spring Boot Project

This Spring Boot project is a solution to the challenge provided as part of the job application process for K-Lagan. The challenge involves building a service with a REST endpoint to handle pricing information from an e-commerce database.

## Table of Contents
- [Overview](#overview)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Running Tests](#running-tests)
- [Project Structure](#project-structure)

## Overview

In the company's e-commerce database, there is a `PRICES` table that reflects the final price and the rate that applies to a chain product between certain dates. The project involves building a Spring Boot application with a REST endpoint that accepts input parameters (Application Date, Product Identifier, String Identifier) and returns relevant pricing information.

## Prerequisites
Before you start working with the PriceAPI project, make sure you have the following prerequisites installed on your system:

### Java Development Kit (JDK)

- **Version:** OpenJDK 17
- **[Download](https://adoptopenjdk.net/)** and install the JDK.

### Integrated Development Environment (IDE)

- **Recommended:** [IntelliJ IDEA](https://www.jetbrains.com/idea/)
   - Import the project into IntelliJ IDEA for an optimized development experience.

### Build Tool

- **Maven**
   - **Version:** 3.x
   - **[Download](https://maven.apache.org/download.cgi)** and install Maven.

### Database

- **H2 Database**
   - H2 is used as a runtime database for the project.
   - No additional setup is required as H2 is included as a dependency.

### Additional Dependencies

- **Spring Boot**
   - **Version:** 3.2.0
   - Spring Boot is the core framework for the project.

- **Spring Data JPA**
   - **Version:** Included with Spring Boot
   - Spring Data JPA for simplified data access.

- **Spring Data REST**
   - **Version:** Included with Spring Boot
   - Spring Data REST for exposing JPA repositories over REST.

- **Lombok**
   - **Version:** 1.18.30
   - Lombok for reducing boilerplate code.

- **JUnit 5**
   - **Version:** 5.10.1
   - JUnit 5 for testing.

- **Mockito**
   - **Version:** 5.7.0
   - Mockito for mocking in tests.

- **MapStruct**
   - **Version:** 1.6.0.Beta1
   - MapStruct for object mapping.

### IDE Configuration

- If you are using IntelliJ IDEA, ensure you have the Lombok plugin installed.
   - **[Lombok Plugin Installation Guide](https://projectlombok.org/setup/intellij)**


## Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/rtase1984/priceapi.git

## Running Tests

The project includes tests for the REST endpoint to validate different scenarios. To run the tests:

Open the PriceControllerTest class in the src/test/java directory.

Right-click inside the file or on a specific test method.

Select "Run <Your Test Class>" or "Run <Your Test Method>".

The test results can be viewed in the "Run" tool window at the bottom of IntelliJ IDEA.

### Postman Collection

- **[PriceAPI Postman Collection](PriceAPI_Postman_Collection.json)**

### Test Cases

1. **TestCase1:**
  - Method: GET
  - URL: `{{baseUrl}}/api/prices/calculate?applicationDate=2020-06-14T10:00:00&productId=35455&brandId=1`

2. **TestCase2:**
  - Method: GET
  - URL: `{{baseUrl}}/api/prices/calculate?applicationDate=2020-06-14T16:00:00&productId=35455&brandId=1`

3. **TestCase3:**
  - Method: GET
  - URL: `{{baseUrl}}/api/prices/calculate?applicationDate=2020-06-14T09:00:00&productId=35455&brandId=1`

4. **TestCase4:**
  - Method: GET
  - URL: `{{baseUrl}}/api/prices/calculate?applicationDate=2020-06-15T10:00:00&productId=35455&brandId=1`

5. **TestCase5:**
  - Method: GET
  - URL: `{{baseUrl}}/api/prices/calculate?applicationDate=2020-06-16T21:00:00&productId=35455&brandId=1`

6. **TestCase6_NoContent:**
  - Method: GET
  - URL: `{{baseUrl}}/api/prices/calculate?applicationDate=2021-06-14T13:00:00&productId=35455&brandId=2`

### Running the Tests

1. Open Postman and import the downloaded collection.
2. Run each test case individually by selecting it from the collection.

Make sure to replace `{{baseUrl}}` with the actual base URL where your PriceAPI is running (e.g., `http://localhost:8080`).

Feel free to explore and modify the test cases according to your specific use cases.

## Project Structure
   
The project follows a standard Spring Boot project structure. Key directories include:

- src/main/java: Contains the main Java source code.

- src/test/java: Contains test classes.



   
