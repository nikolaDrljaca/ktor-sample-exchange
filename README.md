# Ktor project sample

Sample project to showcase server to server communication.
[Ktor](https://ktor.io/) is a backend framework created for Kotlin and is based on
[Coroutines](https://kotlinlang.org/docs/coroutines-overview.html).

*More details about Ktor can be found [here](https://ktor.io/docs/welcome.html).*

Ktor is a lightweight `Kotlin` based framework for creating web applications and microservices.
Main benefits are the relatively small footprint, high modularization and an easy learning curve.

It can be used to create server and client applications, and depending on that and the project requirements
the application can utilize a number of extensions or modules to provide functionality.

Ex: A project can utilize `Content-Negotiation`, `Routing`, `Serialization` etc.

A general project structure example:
- `Application.kt` - "main class" file. This is the file that contains the app entry point.
- `config`
  - `AppConfig.kt`: (*Optional*) A class that can contain application.properties fields to be used throughout the project
  Other configuration files can reside here.
- `di`
  - Contains dependency injection code. See frameworks [koin](https://insert-koin.io) and [kodein](https://kosi-libs.org/kodein-di)
  Other options are available, these are the most used.
- `database`
  - Contains database related code. Different ORMs are available for different sql and nosql based database systems.
  - `DatabaseFactory.kt`
  - `DatabaseFactoryImpl.kt`
  - `BaseDao.kt`
- `features`
  - Here we specify feature based packages. These contain the business value of the application.
  - Furthermore, each feature can be split into data and domain packages/
  - `data`
    - Contains definitions for all data sources. Local or remote. Remote can be message queues from other services or even a REST client.
    - Here reside the database entity and table classes that describe the schema.
    - This package exposes interface based `DataSource` classes.
  - `domain`
    - Domain contains repository interfaces and implementations, and the repositories combine multiple data sources so that routes
    have clean access to data. If a certain entity only has one data source the `repo` layer can be skipped.
    - Also includes any `DTO` based objects and relevant mappers.
  - `controller`
    - This package provides extension functions to `Route` class. It exposes our defined routes for the relevant feature.
    For more info - [Kotlin extension functions](https://kotlinlang.org/docs/extensions.html)

*This project does not reflect the structure as it was intended to be simple.*

This project will only focus on fairly simple implementations to showcase modular server to server communication. The
general idea here behind the project is to have two applications running on different servers that can pass data to each
other back and forth.

Conceptual idea behind project is a *currency exchange.*

## Docker
To run the project in a docker container, an image must be created first.

Open a terminal and run the following command:
```
docker build --tag ktor-sample:debug .
```
*Make sure the current directory is the project root.*

After the build is finished, run the following command to spin up a container with the image:
```
docker run --name ktor-sample -d -p 8080:8080 ktor-sample:debug 
```

To stop the image:
```
docker stop ktor-sample
```
-------------
The same process above can be done for the second module. The only thing that changes is the ports.
```
docker run --name ktor-sample-generator -d -p 8081:8081 ktor-sample-generator:debug 
```

Do keep in mind the names also changes, but the names can be anything.

Module 1 (ktor-sample) will be running on **localhost:8080**, while module 2 (ktor-sample-generator) will be
running on **localhost:8081**.

### Container Connectivity
For containers to have the ability to communicate with each other using ports, we can create docker networks and connect
said containers to the newly made network.

This allows us to know for sure upfront what the url and port of the containers are, since a custom network allows 
containers running on it to be referenced by name.

This means in the source code we can replace `localhost` with `<container_name>`

- To create a network
  - `docker network create <network_name>`
- To assign container to network
  - `docker network connect <network_name> <container_network>`
- List all containers connected to a network
  - `docker inspect <network_name>`
- List all networks a container is connected to
  - `docker inspect <container_name>`

## Details

The project consists of two modules:
- `ktor-sample` This is the root module
- `ktor-sample-generator` This is the second module

### Function

`ktor-sample` takes in a request object that specifies a currency exchange request.
It then returns the values of the converted currency and the rate

`ktor-sample-generator` is responsible for generating a random value that will be sent to the
first application and a response will be forwarded.

The underlying issue here is of course the fact that this is still the traditional `request-response`
workflow and that means that the data exchange has to be initiated by someone/something.

However, the first step is to test viability of two servers exchanging data, later implementations 
will switch to a more reactive approach to the problem as well as dockerized environment for 
deployment.

## Note
The data (semantics of the data, the data itself is important) here is not of importance, rather it is the concept.