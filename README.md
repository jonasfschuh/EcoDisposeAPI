# EcoDispose API for find collection points for environmentally sensitive materials. 
Be ecologically sustainable!.

The EcoDispose API is a web and mobile backend solution for an application that supports community members who want to dispose of environmentally sensitive products sustainably and correctly.

It manages records of collection points, materials, notifications, and receiving entities, and demonstrates how to configure and run the application and database on the Railway cloud platform. Build updates and deployments are automated with the platform’s integration with GitHub.

For example, when the repository receives updates, the cloud service automatically generates a new build based on the configuration and can run the application automatically.

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)

This project is an API built using **Java, Java Spring Boot, Postgres. **
Database and application service uses Railway cloud platform.

- Java 17
- Spring Boot 3 
- H2 (local) 
- PostgreSQL (production) 
- Railway 
- OpenAPI (Swagger) 
- CI/CD direct from GitHub 

## Table of Contents

- [Installation](#installation)
- [Configuration](#configuration)
- [Local Usage](#local-usage)
- [Cloud Usage](#cloud-usage)
- [Class Diagram](#class-diagram)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [Screenshots](#screenshots)

## Installation

1. Clone the repository:

```bash
git clone https://github.com/jonasfschuh/EcoDisposeAPI.git
```

2. Install dependencies with Maven

## Local-usage

1. Start the application with Maven
2. The API will be accessible at http://localhost:8080/swagger-ui/index.html
3. The H2 Database will be accessible at http://localhost:8080/h2-console
with H2 Database credentials: user: admin password: admin 

## Cloud-usage

1. Create a railway account https://railway.app/, and
2. Create a Postgres database.
3. Set the environment variables of API on IntelliJ as generated by the Postgres container on railway.
4. Logged into railway, configure the integration with github to generate continuous integration and delivery.
5. Create a service in railway. When deploying, the application will build and start the application automatically.

Notes: This service allows you to configure a domain to expose this service and make it accessible outside the container.

6. Below is an example generated in my configuration to access swagger-ui.

Interface for testing with swagger-ui - on railway cloud
https://ecodispose-api.railway.app/swagger-ui/index.html

## Class-diagram

Class generated in the mermaid pattern https://mermaid.js.org/intro/

```mermaid
classDiagram
  class CollectionPoint {                   
    -String name
    -ReceivingEntity ReceivingEntity
    -Material[] Materials
    -Address Address
    -Notification[] Notification
  }

  class ReceivingEntity {                    
    -String name
  }

  class Material {                           
    -String alias
    -String description
  }

  class Address {                            
    -String street
    -String number 
    -String neighborhood
    -String city 
  }

  class Notification {                      
    -String description
    -Boolean viewed
  }

  CollectionPoint "1" *-- "1" ReceivingEntity
  CollectionPoint "1" *-- "N" Material
  CollectionPoint "1" *-- "1" Address
  CollectionPoint "1" *-- "N" Notification

```

Example POST data

```
{
  "name": "BestBuy - California",
  "receivingEntity": {
    "name": "Best Buy West La (Store 109)"
  },
  "address": {
    "street": "11301 W Pico Blvd",
    "neighborhood": "Sawtelle 90064",
    "city": "Los Angeles, CA "
  },
  "material": [
    {
      "alias": "battery",
      "description": "Accepts cell phone battery, batteries, car battery"
    }
  ],
  "notification": [
    {
      "description": "BestBuy promotion: for every 10 kilos of discarded batteries, you receive a $10 voucher to spend in the store",
      "viewed": true
    }
  ]
}


{
  "name": "BestBuy - Alaska",
  "receivingEntity": {
    "name": "Best Buy North Anchorage (Tikahtnu Commons) (Store 1760)"
  },
  "address": {
    "street": "1200 N Muldoon Rd",
    "neighborhood": "Ste G TIkahtnu Commons 99504",
    "city": "Anchorage, AK"
  },
  "material": [
    {
      "alias": "battery",
      "description": "Accepts cell phone battery, batteries, car battery"
    }
  ],
  "notification": [
    {
      "description": "BestBuy promotion: for every 10 kilos of discarded batteries, you receive a $10 voucher to spend in the store",
      "viewed": true
    }
  ]
}

```


## API Endpoints

The API provides the following endpoints:

*picture

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request to the repository.

When contributing to this project, please follow the existing code style, [commit conventions](https://www.conventionalcommits.org/en/v1.0.0/), and submit your changes in a separate branch.

## Screenshots

Railway dashboard with CI/CD from github.

Every time a push is made to the main branch of the repository, the application is compiled and deployed automatically.
![railway dashboard](https://github.com/jonasfschuh/JavaRESTfulAPI/blob/main/docs/img/railway_dashboard.gif?raw=true&sanitize=true) 

Log details
![Log details](https://github.com/jonasfschuh/JavaRESTfulAPI/blob/main/docs/img/logs_details.gif?raw=true&sanitize=true)

Postgres running on railway
![Postgres running on railway](https://github.com/jonasfschuh/JavaRESTfulAPI/blob/main/docs/img/postgres%20running%20on%20railway.gif?raw=true&sanitize=true)

Public network to access application. 
![Public network to access application](https://github.com/jonasfschuh/JavaRESTfulAPI/blob/main/docs/img/public%20network%20to%20access%20application.gif?raw=true&sanitize=true)

Retrieve a list of all registered users example
![Retrieve a list of all registered users example](https://github.com/jonasfschuh/JavaRESTfulAPI/blob/main/docs/img/Retrieve%20a%20list%20of%20all%20registered%20users%20example.gif?raw=true&sanitize=true)












