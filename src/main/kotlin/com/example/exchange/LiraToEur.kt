package com.example.exchange

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.math.BigDecimal
import java.math.RoundingMode

fun Double.withDecimals(numOfDecimals: Int) = BigDecimal(this)
    .setScale(numOfDecimals, RoundingMode.HALF_EVEN)
    .toDouble()

fun Application.exchangeRouting() {
    routing {

        get("/exchange") {
//            call.parameters["value"]?.let { value ->
//                val eurValue = convert(value.toDouble())
//                call.respond(mapOf("eur" to eurValue))
//            }

            val request = call.receive<ExchangeRequest>()

        }

    }
}
