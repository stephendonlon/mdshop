package com.donlon.cart.core.domain.events

data class ItemAddedEvent(
    val cartId: String,
    val productId: String,
    val quantity: Int
) : Event