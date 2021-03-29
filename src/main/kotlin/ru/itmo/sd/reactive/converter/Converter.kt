package ru.itmo.sd.reactive.converter

import ru.itmo.sd.reactive.converter.Currency.*

fun convert(
    from: Currency,
    target: Currency,
    amount: Float
): Float =
    conversion[from to target]?.let { it * amount }
        ?: error("Unknown conversion: from $from to $target")

enum class Currency {
    USD,
    EUR,
    RUB
}

private val conversion: Map<Pair<Currency, Currency>, Float> = mapOf(
    (USD to EUR) to 0.84f,
    (USD to RUB) to 72.83f,
    (USD to USD) to 1.0f,
    (EUR to USD) to 1.19f,
    (EUR to RUB) to 86.6f,
    (EUR to EUR) to 1.0f,
    (RUB to USD) to 0.014f,
    (RUB to EUR) to 0.012f,
    (RUB to RUB) to 1.0f
)
