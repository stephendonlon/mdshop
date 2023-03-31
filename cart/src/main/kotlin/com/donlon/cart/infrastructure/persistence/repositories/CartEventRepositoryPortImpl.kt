package com.donlon.cart.infrastructure.persistence.repositories

import com.donlon.cart.core.domain.events.CartEvent
import com.donlon.cart.core.ports.secondary.CartEventRepositoryPort
import com.donlon.cart.infrastructure.persistence.entity.CartEventEntity
import reactor.core.publisher.Mono

class CartEventRepositoryPortImpl(private val cartEventRepository: CartEventRepository) : CartEventRepositoryPort {
    override fun save(cartEvent: CartEvent): Mono<CartEvent> {
        val cartEventEntity = cartEvent.toEntity()
        return cartEventRepository.save(cartEventEntity).map { cartEventEntity.toDomain() }
    }

    private fun CartEvent.toEntity(): CartEventEntity {
        return CartEventEntity(id, cartId, payload, createdTime)
    }

    private fun CartEventEntity.toDomain(): CartEvent {
        return CartEvent(id, cartId, payload, createdTime)
    }
}