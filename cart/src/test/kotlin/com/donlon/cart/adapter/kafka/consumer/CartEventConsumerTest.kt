package com.donlon.cart.adapter.kafka.consumer

import com.donlon.cart.core.domain.events.Action
import com.donlon.cart.core.domain.events.CartEvent
import com.donlon.cart.core.ports.secondary.CartEventRepositoryPort
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import reactor.core.publisher.Mono
import java.time.LocalDateTime

internal class CartEventConsumerTest {
    private val cartEventRepository: CartEventRepositoryPort = mock()
    private val cartEventConsumer = CartEventConsumer(cartEventRepository)

    @Test
    fun `should receive and save CartEvent`() {
        val cartId = "cart-1"
        val cartEvent = CartEvent(1L, cartId, "{\"product\": \"item-1\"}", LocalDateTime.now(), Action.ADD)

        whenever(cartEventRepository.save(cartEvent)).thenReturn(Mono.just(cartEvent))

        cartEventConsumer.receiveCartEvent(cartId, cartEvent)

        verify(cartEventRepository).save(cartEvent)
    }
}