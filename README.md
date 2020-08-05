Transaction service
======================
# Getting Started
This project is based on Spring Boot microservices using the reactive stack, read more info there https://spring.io/reactive

## Frameworks 
* Spring Boot
* Spring Data - Reactive Mongo
* Webflux

## Setup
* Java 1.8
* Maven
* Mongo
* Docker

# Building
## Windows
1. Install [Docker Desktop](https://www.docker.com/products/docker-desktop).
2. Create an image and container for transaction-service using the following code:
```
mvn install
docker build . -t transaction-service
docker run -p 8084:8084 --name transaction-service transaction-service
```
# CRUD

| HTTP Verb  |     `/transaction`  |      `/transaction/{transactionId}`      |   
| ---------- | :---------------: | :---------------: |
| **POST**| ADD new transaction | - |  
| **GET**| GET all transactions | GET transaction by Id |
| **PUT**| - | EDIT transaction by Id|  
| **DELETE**| - |DELETE transaction by Id|  


# Operations
| HTTP Verb  |     `/customers/profile/{customerId}`  |
| ---------- | :---------------: |
| **GET**| GET a detailed customer profile |


# Architecture

![Architecture](https://raw.githubusercontent.com/dmendozy/config-service/master/files/arch.png)

# Authors

* **Danny Mendoza Yenque** - *Everis Bootcamp Microservices July 2020* - [DannyMendoza](https://github.com/dmendozy)
