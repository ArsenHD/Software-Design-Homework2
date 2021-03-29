package ru.itmo.sd.reactive.controller

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.itmo.sd.reactive.converter.Currency
import ru.itmo.sd.reactive.converter.convert
import ru.itmo.sd.reactive.model.Product
import ru.itmo.sd.reactive.repository.ProductRepository
import ru.itmo.sd.reactive.repository.UserRepository

@RestController
@RequestMapping("/products")
class ProductController(
    private val userRepo: UserRepository,
    private val productRepo: ProductRepository
) {
    @GetMapping(params = ["currency"])
    suspend fun getProductsByCurrency(
        @RequestParam("currency") currency: String
    ): String {
        val requiredCurrency = Currency.valueOf(currency)
        return productRepo
            .findAll()
            .mapToCurrency(requiredCurrency)
            .toList()
            .joinToString(System.lineSeparator())
    }

    @GetMapping(params = ["user"])
    suspend fun getProductsByUser(
        @RequestParam("user") username: String
    ): String {
        val user = userRepo.findByName(username)
        val currency = user.currency
        return productRepo
            .findAll()
            .mapToCurrency(currency)
            .toList()
            .joinToString(System.lineSeparator())
    }

    @GetMapping("/new")
    suspend fun addNewProduct(
        @RequestParam("name") name: String,
        @RequestParam("currency") currency: String,
        @RequestParam("price") price: Float
    ) {
        val product = Product(0, name, Currency.valueOf(currency), price)
        productRepo.save(product)
    }

    private fun Flow<Product>.mapToCurrency(currency: Currency): Flow<Product> =
        map {
            val priceInRequiredCurrency = convert(it.currency, currency, it.price)
            it.copy(price = priceInRequiredCurrency)
        }
}