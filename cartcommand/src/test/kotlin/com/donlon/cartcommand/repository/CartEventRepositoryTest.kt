package com.donlon.cartcommand.repository

import com.donlon.cartcommand.service.model.event.CartEvent
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

@MicronautTest(transactional = false)
internal class CartEventRepositoryTest() {

    @Inject
    lateinit var cartEventRepository: CartEventRepository

    @Test
    fun testSaveCartEvent() {
        // Create a CartEvent
        val cartId = "testCartId"
        val payload = "testPayload"
        val createdTime = LocalDateTime.now()
        val cartEvent = CartEvent(cartId = cartId, payload = payload, createdTime = createdTime)

        // Save the CartEvent to the repository
        val savedCartEvent = cartEventRepository.save(cartEvent).block()

        // Verify the saved CartEvent
        assertNotNull(savedCartEvent)
        assertNotNull(savedCartEvent?.id)
        assertEquals(cartId, savedCartEvent?.cartId)
        assertEquals(payload, savedCartEvent?.payload)
        assertEquals(createdTime, savedCartEvent?.createdTime)
    }

}