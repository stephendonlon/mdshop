package com.donlon.cart.adapter.controller

import com.donlon.cart.infrastructure.persistence.repositories.CartEventRepository
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import reactor.core.publisher.Mono

@Controller("/carts")
class CartController(private val cartEventRepository: CartEventRepository) {

    @Get("/{cartEventId}")
    fun getCartEvent(cartEventId: String): Mono<String> {
        return cartEventRepository.findById(cartEventId.toLong()).map { it.cartId }
    }


}