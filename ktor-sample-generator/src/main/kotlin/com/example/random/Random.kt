package com.example.random

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.logging.*
import kotlinx.serialization.json.Json
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun Application.randomRouting() {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }
    val logger = log

    routing {
        get("/random") {
            val request = RandomRequest.createRandomRequest()

            runCatching {
                val response = client.get(
                    urlString = "http://ktor-sample:8080/exchange",
                    block = {
                        contentType(ContentType.Application.Json)
                        setBody(request)
                    }
                ).body<ExchangeResponse>()

                call.respond(
                    RandomResponse(
                        from = response.from,
                        to = response.to,
                        amount = response.amount,
                        result = response.result,
                        timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))
                    )
                )
            }.onFailure {
                logger.error(it)
                logger.info(it.stackTrace.toString())
                call.respond(
                    status = HttpStatusCode.InternalServerError,
                    message = mapOf(
                        "status" to HttpStatusCode.InternalServerError.description,
                        "code" to HttpStatusCode.InternalServerError.value.toString(),
                        "message" to it.localizedMessage
                    )
                )
            }

        }

    }
}