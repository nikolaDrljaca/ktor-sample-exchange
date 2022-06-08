package com.example.random

@kotlinx.serialization.Serializable
data class RandomResponse(
    val from: String,
    val to: String,
    val amount: Double,
    val result: Double,
    val timestamp: String
)

@kotlinx.serialization.Serializable
data class ExchangeResponse(
    val from: String,
    val to: String,
    val amount: Double,
    val result: Double,
)
