# User Profile API

API which implements basic user profile methods

## How to install and use?

### Pre requirements
- [Oracle OpenJDK version 21.0.1](https://jdk.java.net/21/)
- [Apache Maven 3.9.6](https://maven.apache.org/download.cgi)
- [PostgreSQL 16.1](https://www.postgresql.org/download/)

### Installation
1. Clone the repo
    ```shell
    git clone https://github.com/p4sttt/profileService.git
    ```
2. Update application.properties
   you should change `datasource` and  `upload.dir` properties
3. Build project
    ```shell
   mvn package
   ```
4. Run JAR file
   you can find .jar file in `target\`
   ```shell
   java -jar .\target\profileService-0.0.1-SNAPSHOT.jar
   ```

### Usage
You can see all api endpoint in [swagger localhost](http://localhost:8080/swagger-ui/index.html):
`http://localhost:8080/swagger-ui/index.html/` 

### Enjoy