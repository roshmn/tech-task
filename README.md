# TAF for REST Book API

## Getting started

### Start the API
1. To run tests you need run the Book API. Go back to root folder and load `api` folder:
   ```shell
   cd api
   ```
2. Run the API using `maven`. The `maven-wrapper` is already included to the project:
   ```shell
   ./mvnw spring-boot:run
   ```
3. Wait until API will be started. The last log message must contain something like this:
   ```
   Started App in 1.505 seconds (JVM running for 1.797)
   ```
   
## Testing
Test Automation Framework is located in `api_client` folder. 
### Run tests
1. Load this folder through terminal:
   ```shell
   cd api_client
   ```
2. Build project using `Gradle`. The `gradle-wrapper` is already included to the project:
   ```shell
   ./gradlew build
   ```
3. Run tests:
   ```shell
   ./gradlew test
   ```
4. After tests running done, find report in `src/test/resources/reports`, just open `index.html` file with browser.  
   Different reports will be saved into separate folder to save previous results.