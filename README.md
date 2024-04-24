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
