package ru.itmo.sd.reactive.controller

import kotlinx.coroutines.flow.toList
import org.springframework.web.bind.annotation.*
import ru.itmo.sd.reactive.converter.Currency
import ru.itmo.sd.reactive.model.User
import ru.itmo.sd.reactive.repository.UserRepository

@RestController
@RequestMapping("/users")
class UserController(
    private val userRepo: UserRepository
) {
    @GetMapping
    suspend fun getUsers(): String =
        userRepo.findAll()
            .toList()
            .joinToString(System.lineSeparator())

    @GetMapping("/new")
    suspend fun addNewUser(
        @RequestParam("name") name: String,
        @RequestParam("currency") currency: String
    ) {
        val user = User(0, name, Currency.valueOf(currency))
        userRepo.save(user)
    }
}