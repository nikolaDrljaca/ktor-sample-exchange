package com.example

import com.example.plugins.configureRouting
import com.example.random.randomRouting
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8081, host = "localhost") {
        configureRouting()
        //
        randomRouting()
    }.start(wait = true)
}
