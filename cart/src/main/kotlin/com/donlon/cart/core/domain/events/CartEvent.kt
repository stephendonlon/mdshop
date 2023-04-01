package com.donlon.cart.core.domain.events

import io.micronaut.serde.annotation.Serdeable
import java.time.LocalDateTime

@Serdeable
data class CartEvent(
    val id: Long,
    val cartId: String,
    val payload: String,
    val createdTime: LocalDateTime
)
