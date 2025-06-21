
### Build and Run the web-app only (outside of Docker
- a. Typically, the developer run and build via the command `./gradlew bootRun`
- b. For docker, the develop must build and executable jar and run the jar in two distinct commands;
  - `./gradlew bootJar`  // build the executable jar file
  - `java -jar ./build/libs/aaa-simple-webapp-0.0.1-SNAPSHOT.jar`  // run the webapp

### Spin-up database in docker compose
1. create a docker-compose file
  ```
  services:
    postgres:
      image: 'postgres:latest'
      environment:
          POSTGRES_DB: ${DB_NAME_AAA}
          POSTGRES_PASSWORD: ${DB_NAME_AAA}
          POSTGRES_USER: ${DB_NAME_AAA}
    ports:
      - "5432:5432"
  ```
1. create a containser via docker compose
  ```
     DB_NAME_AAA=db_name_aa   docker compose -f compose.yaml --project-name aaa_db up -d
  ```