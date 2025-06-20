
### Build and Run the web-app only (outside of Docker
- a. Typically, the developer run and build via the command `./gradlew bootRun`
- b. For docker, the develop must build and executable jar and run the jar in two distinct commands;
  - `./gradlew bootJar`  // build the executable jar file
  - `java -jar ./build/libs/aaa-simple-webapp-0.0.1-SNAPSHOT.jar`  // run the webapp
