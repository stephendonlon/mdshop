package com.donlon.cartcommand.kafka

import com.donlon.cartcommand.service.model.event.CartEvent
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic
import reactor.core.publisher.Mono

@KafkaClient
interface CartEventProducer {

    @Topic("cart-events")
    fun sendCartEvent(@KafkaKey cartId: String, cartEvent: CartEvent): Mono<CartEvent>
}