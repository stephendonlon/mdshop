package com.donlon.cart.adapter.kafka.testclient

import com.donlon.cart.core.domain.events.CartEvent
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaClient
interface TestCartEventProducer {

    @Topic("cart-events")
    fun sendCartEvent(@KafkaKey cartId: String, cartEvent: CartEvent)
}