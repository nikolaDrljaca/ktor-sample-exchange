package com.example.util

import java.math.BigDecimal
import java.math.RoundingMode


fun Double.withDecimals(numOfDecimals: Int) = BigDecimal(this)
    .setScale(numOfDecimals, RoundingMode.HALF_EVEN)
    .toDouble()
