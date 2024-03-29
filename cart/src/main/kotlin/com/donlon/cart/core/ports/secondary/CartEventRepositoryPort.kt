package com.donlon.cart.core.ports.secondary

import com.donlon.cart.core.domain.events.CartEvent
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CartEventRepositoryPort {
    fun save(cartEvent: CartEvent): Mono<CartEvent>
    fun findAllByCartId(cartId: String): Flux<CartEvent>
}