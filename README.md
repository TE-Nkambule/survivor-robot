## survivor-management

## A Robot Apocalyse project written in Java showing how to create REST API with Springboot

## Description

This project is a critical component in the fight against robots that have taken over the world. As one of the last surviving software engineers, my task is to develop and manage a system that tracks and organizes information about human survivors, their resources, and provides insights into the status of the robot uprising.

## Table of content

- Requirement
- Installation
- Setting up the Database
- Dependencies Section
- Implementation
- API Documentation Section

# Requirements
- A survivor must have: Name, Age, Gender, ID, Last location (latitude, longitude), Inventory of resources (Water, Food, Medication, Ammunition)
- Update survivor location
- Flag survivor as infected if 3 or more people have reported them.
- The system must provide:
        - Percentage of infected survivors.
        - Percentage of non-infected survivors.
        - List of infected survivors.
        - List of non-infected survivors.
        - List of robots.


# Installation

- Clone this project robot-apocalypse-api.git

- Navigate to the project directory: 'cd survivor-management'
- Run the application: './mvnw spring-boot:run'
- Download Intellij to write you code https://www.jetbrains.com/idea/download/?section=windows 
- Can alternatively open the project in Intellij

# Setting up the Database

- Download and install PostgreSQL - https://www.postgresql.org/download/
- Once the installation is complete.  There will be a database client pgAdmin installed on your machine.  Please open pgAdmin.
- The default username when pgAdmin opens for the first time is 'postgres'.
- Create the password to also be 'admin'.
- Open pgAdmin, and then choose option Create > Database.
- Name the database 'robotapocalypse'.
- Open Tools > Query Tool.
- Click option Open File, and then open the attached file 'robotapocalypse.sql'.
- Run the opened script 'robotapocalypse.sql' by clicking option Execute/Refresh.

# Dependencies Section
- Lookup parent from repository
- Spring Boot Data JPA
- Spring Boot Starter Web
- PostgreSQL JDBC Driver 
- Lombok
- Spring Boot Starter Test 

# Implementation

- Programming Language: Java with Spring Boot.
- Database: PostgreSQL for storing survivor and robot information.

# API Documentation Section
- Spring boot information https://spring.io/tools/
- I have attached a word document that has all my APIs and endpoints.
