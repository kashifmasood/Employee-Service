# Employee-Service
A simple web application that exposes REST operations for employees.

## Employee Data Source #
On startup, the application automatically loads a list of sample employees from 
a CSV file located in the following location:

    Employee-Service/src/main/resources/employee_data.csv

If you make any changes to this file, please make sure to restart the web application.

# Building and Running #

### Dependencies ###
- Java 1.8

### Building Locally ###
- Clone this repository

  `git clone https://github.com/kashifmasood/Employee-Service.git`
  
- cd into `Employee-Service`

- build project `./gradlew clean build -i`

- to run tests only: `./gradlew test -i`

## Running Web Application ##
 
- ``./gradlew bootRun`` 

# Interacting With Web Application #
The web application exposes several RESTful APIs to interact with the employee data.

Once the application starts, it will listen on port 8080 on localhost for API requests. 

Following is a description of the RESTful APIs exposed by this application:
---
#### Get All Active Employees ####

    
Retrieve a list of all ACTIVE employees in the system
    
| Request Type | URI | Body |
|--------------|-----|------|
| GET|http://localhost:8080/employee|

---
#### Get Employee By Id ####

Get details on a particular employee by their ID

**Note:** Only employees with a status of ACTIVE can be retrieved
    
| Request Type | URI | Body |
|--------------|-----|------|
| GET|http://localhost:8080/employee/{id}|

---
#### Update Employee ####

Update an existing employee information by passing in the information as a JSON in the body of the API call:

`````
{
     "firstName": "Michael",
     "middleInitial": "A",
     "lastName": "Jackson",
     "dateOfBirth": "1990-12-01",
     "dateOfEmployment": "2005-01-01"
 }
 ````` 
 
    
| Request Type | URI | Body |
|--------------|-----|------|
| PUT|http://localhost:8080/employee/{id}|Employee JSON 

---
#### Create New Employee ####

Create a new employee by passing in the information as a JSON in the body of the API call:

`````
{
     "id": 200,
     "firstName": "Michael",
     "middleInitial": "A",
     "lastName": "Jackson",
     "dateOfBirth": "1990-12-01",
     "dateOfEmployment": "2005-01-01"
 }
 ````` 
 
 **Note**: When creating a new Employee, please ensure that the ID is unique across all the empoyees in the system.
    
| Request Type | URI | Body |
|--------------|-----|------|
| POST|http://localhost:8080/employee|Employee JSON 

---
#### Delete Employee ####

Delete an existing employee. This API changes the status of the employee to INACTIVE. 

Once an employee is deleted (inactived), they cannot be retrieved via any APIs. 

**Note:** In order to call the Delete API, you will need to pass the Header key with its value as indicated below.
    
| Request Type | URI | Body | Header Key/Value |
|--------------|-----|------| ------- |
| DELETE|http://localhost:8080/employee/{id}| |Auth-Key=FyjmRx6sg
