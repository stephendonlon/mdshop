package com.donlon.cart.infrastructure.persistence.repositories

import com.donlon.cart.core.domain.events.Action
import com.donlon.cart.core.domain.events.CartEvent
import com.donlon.cart.core.ports.secondary.CartEventRepositoryPort
import com.donlon.cart.infrastructure.persistence.entity.ActionEntity
import com.donlon.cart.infrastructure.persistence.entity.CartEventEntity
import jakarta.inject.Singleton
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Singleton
class CartEventRepositoryPortImpl(private val cartEventRepository: CartEventRepository) : CartEventRepositoryPort {
    override fun save(cartEvent: CartEvent): Mono<CartEvent> {
        val cartEventEntity = cartEvent.toEntity()
        return cartEventRepository.save(cartEventEntity).map { cartEventEntity.toDomain() }
    }

    override fun findAllByCartId(cartId: String): Flux<CartEvent> {
        return cartEventRepository.findAllByCartId(cartId).map { it.toDomain() }
    }

    private fun CartEvent.toEntity(): CartEventEntity {
        return CartEventEntity(id, cartId, payload, createdTime, ActionEntity.valueOf(action.name))
    }

    private fun CartEventEntity.toDomain(): CartEvent {
        return CartEvent(id, cartId, payload, createdTime, Action.valueOf(action.name))
    }
}