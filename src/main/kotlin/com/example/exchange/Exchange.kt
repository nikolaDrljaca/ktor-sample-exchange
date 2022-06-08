package com.example.exchange

import com.example.util.withDecimals
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.exchangeRouting() {
    routing {

        get("/exchange") {
            runCatching {
                val request = call.receive<ExchangeRequest>()
                val resultAmount = exchange(request.from, request.to, request.amount)
                    .withDecimals(2)

                call.respond(
                    status = HttpStatusCode.OK,
                    message = ExchangeResponse(
                        from = request.from.trim().uppercase(),
                        to = request.to.trim().uppercase(),
                        amount = request.amount,
                        result = resultAmount
                    ),
                )
            }.onFailure { exception ->
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = mapOf(
                        "status" to HttpStatusCode.BadRequest.description,
                        "code" to HttpStatusCode.BadRequest.value.toString(),
                        "message" to exception.localizedMessage
                    )
                )
            }
        }

    }
}
