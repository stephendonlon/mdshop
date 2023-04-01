package com.donlon.cart.infrastructure.persistence.entity

import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.time.LocalDateTime

@MappedEntity("cart_event")
data class CartEventEntity(
    @field:Id
    val id: Long,
    val cartId: String,
    val payload: String,
    @field:DateCreated
    val createdTime: LocalDateTime,
    val action: ActionEntity
)