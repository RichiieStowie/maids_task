# Library Management System

## Overview

This project is a Library Management System API built using Spring Boot. It provides functionalities to manage books, patrons, and borrowing records.

## Prerequisites

Before running the application or tests, make sure you have the following installed:

- Java Development Kit (JDK) 8 or higher
- Maven

## Running the Application

To run the application locally, follow these steps:

1. Clone this repository:

    ```bash
    git clone <repository-url>
    ```

2. Navigate to the project directory:

    ```bash
    cd library-management-system
    ```

3. Build the project using Maven:

    ```bash
    mvn clean package
    ```

4. Run the application using the Spring Boot Maven plugin:

    ```bash
    mvn spring-boot:run
    ```

The application should now be running locally on [http://localhost:8080](http://localhost:8080).

## Running Tests

To run the tests for the application, execute the following command:

```bash
mvn test
```

## API Documentation

Once the application is running, you can access the API documentation at:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

This Swagger UI provides a detailed overview of all the endpoints available in the API along with their request and response schemas.

## Configuration

The application uses an H2 in-memory database by default. You can modify the database configuration in the `application.properties` file.
