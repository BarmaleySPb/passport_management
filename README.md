# job4j_passport_management



## Study project "job4j_passport_management".
### training course Job4j_MIDDLE

Technologies used:
PostgreSQL, Maven, SpringBoot, Docker, Docker-compose

Screenshots of the application:
...

### To run an app via Docker Compose:
1) Clone the project from GitHub:
   ```
   git clone https://github.com/BarmaleySPb/passport_management   
2) Go to the project, build the project:
   ```
   cd ./passport_management
   mvn install
3) Create image:
   ```
   docker build -t passport_management .
4) Next, run it:
   ```
   docker-compose up -d
5) To view the status of a container, use the command:
   ```
   docker-compose ps

### To run an app via K8s:
1) Execute the command below. It creates a secret from the specified file:
   ```
   kubectl apply -f postgresdb-secret.yml
2) Bring ConfigMap into the cluster:
   ```
   kubectl apply -f postgresdb-configmap.yml
3) Run it:
   ```
   kubectl apply -f postgresdb-deployment.yml
4) Let's start creating a deployment:
   ```
   kubectl apply -f spring-deployment.yml
5) Then enter the command below. The service command returns us a URL where we can connect to the service from the outside:
   ```
   minikube service spring-boot-service

### Contacts:
[![Telegram](https://img.shields.io/badge/-telegram-grey?style=flat&logo=telegram&logoColor=white)](https://t.me/Evgeny_Zakharov)&nbsp;
[![Email](https://img.shields.io/badge/@%20email-005FED?style=flat&logo=mail&logoColor=white)](mailto:e.g.zakharov@gmail.com)&nbsp;