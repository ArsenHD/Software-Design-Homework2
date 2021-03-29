package ru.itmo.sd.reactive.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import ru.itmo.sd.reactive.converter.Currency
import org.springframework.data.relational.core.mapping.Table

@Table("Products")
data class Product(
    @Id
    val id: Long = 0,

    @Column
    val name: String = "product name",

    @Column
    val currency: Currency = Currency.RUB,

    @Column
    val price: Float = 0.0f
) {
    override fun toString(): String = "$name: $price"
}