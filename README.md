# Flight Control
Flight control Spring Boot REST API.

This project uses:

+ Spring Data JPA
+ H2 Database (in-memory)
+ Lombok
+ Swagger2

## Installation
Import project into your favourite IDE.
Clone this repository:
```bash
git clone https://github.com/zonkje/flight-control.git
```

Quick info on how to run a Spring Boot application
1. Build using maven: ```mvn install```
2. Execute: ```java -jar target/flight-control-api-0.0.1-SNAPSHOT.jar```

Access the deployed web application at: http://localhost:8080/api/v1

Database is automatically populated with example flights data.

## Usage

The application has two functionalities:

1. Freight weight data for a given flight

   GET ```http://localhost:8080/api/v1/flight/weight/{flightNumber}/{flightDate}```

   Example: http://localhost:8080/api/v1/flight/weight/5110/2015-09-07T10:10:42

2. Data on arrivals and departures from a given airport on a specific date

   GET ```http://localhost:8080/api/v1/flight/airport/{airportIATACode}/{flightDate}```

   Example: http://localhost:8080/api/v1/flight/airport/SEA/2017-06-08T10:30:10

## Docs

Visit this URL in your browser to get documentation of Flight Control API in JSON format:

 ```http://localhost:8080/api/v1/swagger2/api-docs```
 
You can also navigate to:

```http://localhost:8080/api/v1/swagger-ui/```

to access the Swagger UI
