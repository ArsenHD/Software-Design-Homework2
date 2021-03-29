package ru.itmo.sd.reactive.repository

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import ru.itmo.sd.reactive.model.User

interface UserRepository : CoroutineCrudRepository<User, Long> {
    suspend fun findByName(name: String): User
}