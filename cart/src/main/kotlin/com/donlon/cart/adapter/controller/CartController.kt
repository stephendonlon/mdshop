package com.donlon.cart.adapter.controller

import com.donlon.cart.application.services.CartService
import com.donlon.cart.core.domain.Cart
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import reactor.core.publisher.Mono

@Controller("/carts")
class CartController(private val cartService: CartService) {

    @Get("/{cartId}")
    fun getCart(cartId: String): Mono<Cart> {
        return cartService.getCartById(cartId)
    }
}