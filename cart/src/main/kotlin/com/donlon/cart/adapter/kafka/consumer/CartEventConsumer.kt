package com.donlon.cart.adapter.kafka.consumer

import com.donlon.cart.core.domain.events.CartEvent
import com.donlon.cart.core.ports.secondary.CartEventRepositoryPort
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic
import jakarta.inject.Singleton

@Singleton
@KafkaClient
class CartEventConsumer(private val cartEventRepository: CartEventRepositoryPort) {

    @Topic("cart-events")
    fun receiveCartEvent(@KafkaKey cartId: String, cartEvent: CartEvent) {
        cartEventRepository.save(cartEvent)
            .subscribe(
                { println("CartEvent saved successfully: ${it.id}") },
                { error -> println("Error saving CartEvent: ${error.message}") }
            )
    }

}
