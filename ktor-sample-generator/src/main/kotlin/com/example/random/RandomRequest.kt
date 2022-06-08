package com.example.random

import com.example.util.withDecimals
import kotlin.random.Random

@kotlinx.serialization.Serializable
data class RandomRequest(
    val amount: Double,
    val from: String,
    val to: String
) {
    companion object {
        fun createRandomRequest(): RandomRequest {
            val randomNumber = Random.nextDouble(10.0, 10000.0)
                .withDecimals(2)

            val currencyList = Currency.values()
            val fromCurrency = currencyList[Random.nextInt(0, currencyList.size)].id
            val toCurrency = currencyList[Random.nextInt(0, currencyList.size)].id

            return RandomRequest(
                amount = randomNumber,
                from = fromCurrency,
                to = toCurrency
            )
        }
    }
}

private enum class Currency(val id: String) {
    EUR("eur"),
    TL("tl"),
    CAD("cad"),
    USD("usd"),
    GBP("gbp"),
}
