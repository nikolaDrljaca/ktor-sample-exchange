package com.example

import com.example.exchange.exchangeRouting
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "localhost") {
        configureRouting()
        configureHTTP()
        configureSerialization()

        //
        exchangeRouting()
    }.start(wait = true)
}
