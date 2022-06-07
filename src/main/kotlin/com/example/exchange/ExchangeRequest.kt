package com.example.exchange

@kotlinx.serialization.Serializable
data class ExchangeRequest(
    val amount: Double,
    val of: String,
    val to: String
)
