# Ktor project sample

Sample project to showcase server to server communication.
[Ktor](https://ktor.io/) is a backend framework created for Kotlin and is based on
[Coroutines](https://kotlinlang.org/docs/coroutines-overview.html).

*More details about Ktor can be found [here](https://ktor.io/docs/welcome.html).*

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