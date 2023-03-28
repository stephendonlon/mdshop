package com.donlon.cartcommand.controller

import com.donlon.cartcommand.controller.dto.CartCreateResponse
import com.donlon.cartcommand.service.CartCommandService
import com.donlon.cartcommand.service.model.CartCommand
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import reactor.core.publisher.Mono

@Controller("/carts")
class CartCommandController(private val cartCommandService: CartCommandService) {

    @Post
    fun createCart(@Body command: CartCommand): Mono<CartCreateResponse> {
        return cartCommandService.addToNewCart(command)
            .map { CartCreateResponse(it.cartId) }
    }

    @Post("/{cartId}")
    fun addToCart(@PathVariable cartId: String, @Body command: CartCommand): Mono<Void> {
        return cartCommandService.update(command, cartId).then()
    }

}