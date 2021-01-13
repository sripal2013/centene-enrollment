## General info
A microservice for tracking the status of enrollees in a health care program. This project is for centene-enrollment with enrollees and dependents.
	
## Setup
To run this project,
```
$ mvn clean install
$ mvn spring-boot:run
```
## Entities
1) Enrollee
2) Dependent

## Database 
In-memory H2 Database with SpringBoot 

<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>

## EndPoints Uris and Request and Response

GET : http://localhost:8080/enrollment/all

POST: http://localhost:8080/enrollment/create

{
"name" :"John",
"activationStatus" : "true",
"dateOfBirth" : "12-1-1995",
"phoneNumber" : "123-343-5676"
}

PUT: http://localhost:8080/enrollment/update

{
"id" : "1",
"name" :"John David",
"activationStatus" : "true",
"dateOfBirth" : "12-1-1995",
"phoneNumber" : "123-343-5676"
}

GET : http://localhost:8080/enrollment/1

DELETE : http://localhost:8080/enrollment/1

GET: http://localhost:8080/enrollment/1/dependents

POST: http://localhost:8080/enrollment/1/dependent
{
"name" :"Scott",
"dateOfBirth" : "1-1-2010"
}

{
	"name": "Matt",
	"dateOfBirth": "1-1-2013"
}

PUT: http://localhost:8080/enrollment/1/dependent
{
"id" : "1",
"name" :"John",
"dateOfBirth" : "1-1-2010"

}

GET: http://localhost:8080/enrollment/dependent/1

DELETE : http://localhost:8080/enrollment/1/dependent/2

