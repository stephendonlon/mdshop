package com.donlon.cart.adapter.controller

import com.donlon.cart.core.domain.Cart
import com.donlon.cart.infrastructure.persistence.entity.ActionEntity
import com.donlon.cart.infrastructure.persistence.entity.CartEventEntity
import com.donlon.cart.infrastructure.persistence.repositories.CartEventRepository
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime


@MicronautTest(transactional = false)
class CartControllerIT {

    @Inject
    @field:Client("/")
    lateinit var httpClient: HttpClient

    @Inject
    lateinit var cartEventRepository: CartEventRepository

    private val cartId1 = "cart-1"
    private val cartId2 = "cart-2"

    @Test
    fun `test consumed events are returned as a cart with correct items`() {

        // Simulate consuming events by saving them to the database
        createCartEvents().forEach { event ->
            cartEventRepository.save(event).block()
        }

        // Act
        val response = httpClient.toBlocking().retrieve("/carts/cart-1", Cart::class.java)

        // Assert
        assertEquals(cartId1, response.id)
        assertEquals(3, response.items.size)

        val item1 = response.items.firstOrNull { it.productId == "product-1" }
        assertEquals(2, item1!!.quantity)

        val item2 = response.items.firstOrNull { it.productId == "product-2" }
        assertEquals(3, item2!!.quantity)

        val item3 = response.items.firstOrNull { it.productId == "product-3" }
        assertEquals(1, item3!!.quantity)

    }

    private fun createCartEvents(): List<CartEventEntity> {

        val cartEventEntity1 = CartEventEntity(
            id = 1L,
            cartId = cartId1,
            payload = "{\"productId\": \"product-1\", \"quantity\": 2}",
            createdTime = LocalDateTime.now(),
            action = ActionEntity.ADD
        )

        val cartEventEntity2 = CartEventEntity(
            id = 2L,
            cartId = cartId1,
            payload = "{\"productId\": \"product-2\", \"quantity\": 3}",
            createdTime = LocalDateTime.now(),
            action = ActionEntity.ADD
        )

        val cartEventEntity3 = CartEventEntity(
            id = 3L,
            cartId = cartId1,
            payload = "{\"productId\": \"product-3\", \"quantity\": 1}",
            createdTime = LocalDateTime.now(),
            action = ActionEntity.ADD
        )

        val cartEventEntity4 = CartEventEntity(
            id = 4L,
            cartId = cartId2,
            payload = "{\"productId\": \"product-1\", \"quantity\": 4}",
            createdTime = LocalDateTime.now(),
            action = ActionEntity.ADD
        )

        val cartEventEntity5 = CartEventEntity(
            id = 5L,
            cartId = cartId2,
            payload = "{\"productId\": \"product-4\", \"quantity\": 1}",
            createdTime = LocalDateTime.now(),
            action = ActionEntity.ADD
        )

        return listOf(
            cartEventEntity1,
            cartEventEntity2,
            cartEventEntity3,
            cartEventEntity4,
            cartEventEntity5
        )
    }
}