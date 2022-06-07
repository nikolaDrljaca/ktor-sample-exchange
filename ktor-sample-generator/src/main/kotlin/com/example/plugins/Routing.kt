package com.example.plugins

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import kotlinx.coroutines.delay
import org.slf4j.Logger

fun Application.configureRouting() {
    val client = HttpClient(CIO)
    val logger = log

    routing {
        get("/test/{value}") {
            val value = call.parameters["value"] ?: "0"

            val response = client.get("http://localhost:8080/tleur/$value")
                .bodyAsText()

            call.respond(response)
            logger.info("Message: $response")


        }

        get("/") {
            call.respond("Hello world from server 2.")
        }
    }
}
