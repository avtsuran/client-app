# Сlient app
Client application for monitoring data about users. [Server application](https://github.com/avtsuran/server-app/)
## Prerequisites
You will need the following things properly installed on your computer:

* [Maven](https://maven.apache.org/)
* [Java](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [PostgeSQL](https://www.postgresql.org/)
* [ActiveMQ](http://activemq.apache.org/)

Before using app you should configure file src/main/resources/application.properties

## Run app
Before running app you should run [server-app](https://github.com/avtsuran/server-app/)

To run app use next command:
````
$ mvn spring-boot:run
````

### Go to [http://localhost:8080/](http://localhost:8080/)