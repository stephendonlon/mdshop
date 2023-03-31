package com.donlon.cart.infrastructure.persistence.repositories

import com.donlon.cart.core.domain.events.CartEvent
import com.donlon.cart.infrastructure.persistence.entity.CartEventEntity
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import reactor.test.StepVerifier


internal class CartEventRepositoryPortImplTest {
    private val cartEventRepository: CartEventRepository = mock()
    private val cartEventRepositoryPortImpl = CartEventRepositoryPortImpl(cartEventRepository)

    @Test
    fun `should save CartEvent`() {
        val nowTime =  LocalDateTime.now()

        val cartEvent = CartEvent(1L, "cart-1", "{\"product\": \"item-1\"}", nowTime)
        val cartEventEntity = CartEventEntity(1L, "cart-1", "{\"product\": \"item-1\"}", nowTime)

        whenever(cartEventRepository.save(cartEventEntity)).thenReturn(Mono.just(cartEventEntity))

        val result = cartEventRepositoryPortImpl.save(cartEvent)

        StepVerifier.create(result)
            .assertNext {
                assertEquals(cartEvent, it)
            }
            .verifyComplete()
    }
}