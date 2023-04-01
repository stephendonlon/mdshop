package com.donlon.cart.application.services

import com.donlon.cart.core.domain.Cart
import com.donlon.cart.core.domain.events.Event
import com.donlon.cart.core.domain.events.serializer.EventDeserializer
import com.donlon.cart.core.ports.secondary.CartEventRepositoryPort
import jakarta.inject.Singleton
import reactor.core.publisher.Mono


@Singleton
class CartService(
    private val cartEventRepositoryPort: CartEventRepositoryPort,
    private val eventDeserializer: EventDeserializer
) {

    fun getCartById(cartId: String): Mono<Cart> {
        return cartEventRepositoryPort.findAllByCartId(cartId)
            .collectList()
            .map { cartEvents ->
                cartEvents.fold(Cart(cartId, emptyList())) { cart, event ->
                    // Deserialize the event payload into a domain event
                    val domainEvent: Event = eventDeserializer.deserialize(event.payload, event.action)

                    cart.apply(domainEvent)
                }
            }
    }

}