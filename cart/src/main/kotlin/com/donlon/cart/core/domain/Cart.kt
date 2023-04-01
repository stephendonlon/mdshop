package com.donlon.cart.core.domain

import com.donlon.cart.core.domain.events.Event
import com.donlon.cart.core.domain.events.ItemAddedEvent

data class Cart(
    val id: String,
    val items: List<CartItem>
) {
    fun apply(event: Event): Cart {
        return when (event) {
            is ItemAddedEvent -> applyItemAddedEvent(event)
            // Add more cases here for different event types
            else -> this
        }
    }

    private fun applyItemAddedEvent(event: ItemAddedEvent): Cart {
        val updatedItems = items.toMutableList().apply {
            val index = indexOfFirst { it.productId == event.productId }

            if (index >= 0) {
                this[index] = this[index].copy(quantity = this[index].quantity + event.quantity)
            } else {
                add(CartItem(event.productId, event.quantity))
            }
        }

        return copy(items = updatedItems)
    }
}