# InvestoBull_Assignment

## Introduction
Welcome to Stock Management! This project is an application for processing financial data that showcases Java, Spring Boot, and Spring Data JPA. It provides endpoints for storing JSON data, retrieving the first ORB candle time, and generating new interval candles. It's perfect for solo projects.


## Video Walkthrough of the Project
[Embed or link any video walkthroughs that demonstrate how to use your application.]

## Features
- Feature 1: JSON files can be used to store data in a database.
- Feature 2: Obtain the first ORB candle within a specified time frame.
- Feature 3: Get the list of all candles in the given Interval

## Installation & Getting Started
To install and run this Spring Boot project locally, follow these steps:

1. Clone this repository: `git clone https://github.com/your-username/your-project.git`
2. Navigate to the project directory: `cd your-project`
3. Build and run the project: `mvn spring-boot:run`

## API Endpoints

### Endpoint 1: Store Candles
This endpoint reads the attached JSON file and stores its contents in the H2 Database using Spring Data JPA.

- HTTP Method: POST
- Endpoint: `http://localhost:8080/store-candles?api-key=bvxshxsh`
- Request: None
- Response: Status message indicating success or failure

### Endpoint 2: Get Candles
This endpoint retrieves stored candles from the H2 Database.

- HTTP Method: GET
- Endpoint: `http://localhost:8080/get-candles?api-key=bvxshxsh`
- Request: None
- Response: JSON array containing stored candles

### Endpoint 3: First Orb Candle
This endpoint takes a time parameter and returns the time when the 1st ORB candle was generated.

- HTTP Method: GET
- Endpoint: `http://localhost:8080/first-Orb-candle/{time}?api-key=bvxshxsh`
- Request: URL parameter `time`
- Response: JSON object containing the ORB candle generation time

### Endpoint 4: New Interval Candles
This endpoint takes an interval parameter and generates a new JSON representing candles.

- HTTP Method: GET
- Endpoint: `http://localhost:8080/new-interval-candles/{time}?api-key=bvxshxsh`
- Request: URL parameter `time`
- Response: JSON object containing the newly created candles

## Technology Stack
This project is built using the following technologies:

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- H2 Database

## About
This project was developed as a solo assignment for a company recruitment process.


