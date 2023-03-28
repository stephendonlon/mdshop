package com.donlon.cartcommand.service

import com.donlon.cartcommand.service.model.CartCommand
import com.donlon.cartcommand.service.model.event.CartEvent
import reactor.core.publisher.Mono

interface CartCommandService {

    fun addToNewCart(cartCommand: CartCommand): Mono<CartEvent>
    fun update(cartCommand: CartCommand, cartId: String): Mono<CartEvent>

}