FROM openjdk
WORKDIR passport_management
ADD target/passport_management-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT java -jar app.jar