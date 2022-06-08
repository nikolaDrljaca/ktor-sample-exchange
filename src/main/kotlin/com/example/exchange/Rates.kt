package com.example.exchange

object Rates {
    private const val BASE = 1.0
    private const val TL = 17.89
    private const val CAD = 1.34
    private const val USD = 1.06
    private const val GBP = 0.85

    fun convertCodeToRate(code: String): Double {
        return when(code.trim().lowercase()) {
            "eur" -> BASE
            "tl" -> TL
            "cad" -> CAD
            "usd" -> USD
            "gbp" -> GBP
            else -> throw RuntimeException("Invalid or unsupported currency code: $code")
        }
    }
}

fun exchange(from: String, to: String, amount: Double): Double {
    val fromRate = Rates.convertCodeToRate(from)
    val toRate = Rates.convertCodeToRate(to)

    if (from.trim().lowercase() == "eur")
        return amount * toRate

    return amount * (toRate / fromRate)
}