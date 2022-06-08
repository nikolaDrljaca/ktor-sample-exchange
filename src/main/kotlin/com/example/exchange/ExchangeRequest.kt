package com.example.exchange

@kotlinx.serialization.Serializable
data class ExchangeRequest(
    val amount: Double,
    val from: String,
    val to: String
)
