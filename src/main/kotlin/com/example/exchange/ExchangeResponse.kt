package com.example.exchange

@kotlinx.serialization.Serializable
data class ExchangeResponse(
    val from: String,
    val to: String,
    val amount: Double,
    val result: Double
)