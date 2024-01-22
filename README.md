# Conference Demo Application

## Overview
A Spring Boot application designed for managing conference sessions and speakers, using Spring Data JPA and PostgreSQL.

## Features
- Speaker management (CRUD operations)
- Session management (CRUD operations)

## Getting Started
git clone
cd conference-demo
mvn clean install
mvn spring-boot:run

## API Endpoints
- /api/v1/speakers
- /api/v1/sessions

## Configuration
Set environment variable DB_URL for the database URL. Default username is postgres and password is password. Configure Hibernate settings as needed in application.properties.
