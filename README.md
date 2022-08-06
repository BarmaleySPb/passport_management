# job4j_passport_management



## Study project "job4j_passport_management".
### training course Job4j_MIDDLE

Technologies used:
PostgreSQL, Maven, SpringBoot

Screenshots of the application:
...

### To run an app via Docker Compose:
1) clone the project from GitHub
    ```
   git clone https://github.com/BarmaleySPb/passport_management   
2) go to the project, build the project
   ```
   cd our_project_path
   mvn install
3) create file Dockerfile and fill it
    ```
   touch Dockerfile
   nano Dockerfile
   
        FROM openjdk
        WORKDIR passport_management
        ADD target/passport_management-0.0.1-SNAPSHOT.jar app.jar
        ENTRYPOINT java -jar app.jar
4) create image
    ```
   docker build -t passport_management .
5) create file docker-compose.yml and fill it
    ```
   touch docker-compose.yml
   nano docker-compose.yml
   
      version: "3.9"
      services:
        db:
          image: postgres
          container_name: db
          environment:
            - POSTGRES_PASSWORD=password
            - POSTGRES_USER=postgres
            - POSTGRES_DB=passport_management
            - PGDATA=/var/lib/postgresql/data
          volumes:
            - ./db/passport_management/data:/var/lib/postgresql/data
          ports:
            - 5432:5432
          restart: always
        app:
          image: passport_management
          container_name: passport_management
          environment:
            - SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/passport_management
            - SPRING_DATASOURCE_USERNAME=postgres
            - SPRING_DATASOURCE_PASSWORD=password
          ports:
            - 8080:8080
          depends_on:
            - db
6) Next, run it
    ```
   docker-compose up
7) To view the status of a container, use the command
    ```
   docker-compose ps

### Contacts:
[![Telegram](https://img.shields.io/badge/-telegram-grey?style=flat&logo=telegram&logoColor=white)](https://t.me/Evgeny_Zakharov)&nbsp;
[![Email](https://img.shields.io/badge/@%20email-005FED?style=flat&logo=mail&logoColor=white)](mailto:e.g.zakharov@gmail.com)&nbsp;