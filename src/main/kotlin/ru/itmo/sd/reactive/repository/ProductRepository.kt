package ru.itmo.sd.reactive.repository

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import ru.itmo.sd.reactive.model.Product

interface ProductRepository : CoroutineCrudRepository<Product, Long>