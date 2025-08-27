# Train App
A small **Spring Boot + Angular** demo app for modeling and sorting a train of wagons, based on adjacency rules.
## Features
- Add, remove, replace wagons
- Validate train based on two rules:
  - Wagons A and B cannot be adjacent
  - At most two wagons A can be in sequence (no AAA)
- If possible, sort the train into a valid configuration
## Setup
### Prerequisites
- [Java 21+](https://adoptium.net/)
- [Maven 3.9+](https://maven.apache.org/)
- [Node.js 20+](https://nodejs.org/) and npm
### 1. Run Backend (from trainapp)
```bash
mvn spring-boot:run
```
Backend runs on: http://localhost:8080
### 2. Run Frontend
```bash
cd train-ui
npm install
ng serve
```
Frontend runs on: http://localhost:4200
