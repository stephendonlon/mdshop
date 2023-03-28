package com.donlon.cartcommand.service

import com.donlon.cartcommand.repository.CartEventRepository
import com.donlon.cartcommand.service.model.CartCommand
import com.donlon.cartcommand.service.model.event.CartEvent
import com.donlon.cartcommand.service.uuid.UUIDGenerator
import io.micronaut.serde.ObjectMapper
import jakarta.inject.Singleton
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Singleton
class CartCommandServiceImpl(
    private val cartEventRepository: CartEventRepository,
    private val objectMapper: ObjectMapper,
    private val uuidGenerator: UUIDGenerator
) : CartCommandService {

    override fun addToNewCart(cartCommand: CartCommand): Mono<CartEvent> {
        val cartId = uuidGenerator.generate();
        return saveEvent(cartCommand, cartId)
    }

    override fun update(cartCommand: CartCommand, cartId: String): Mono<CartEvent> {
        return saveEvent(cartCommand, cartId)
    }

    private fun saveEvent(
        cartCommand: CartCommand,
        cartId: String
    ): Mono<CartEvent> {
        val commandAsString = objectMapper.writeValueAsString(cartCommand)

        val cartEvent = CartEvent(
            cartId = cartId,
            payload = commandAsString,
            createdTime = LocalDateTime.now()
        )

        return cartEventRepository.save(cartEvent)
    }
}