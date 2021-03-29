package ru.itmo.sd.reactive.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import ru.itmo.sd.reactive.converter.Currency

@Table("Users")
data class User(
    @Id
    val id: Long = 0,

    @Column
    val name: String = "user name",

    @Column
    val currency: Currency = Currency.RUB,
) {
    override fun toString(): String = "$name: $currency"
}