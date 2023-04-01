package com.donlon.cart.core.domain.events.serializer

import com.donlon.cart.core.domain.events.Action
import com.donlon.cart.core.domain.events.Event
import com.donlon.cart.core.domain.events.ItemAddedEvent
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import jakarta.inject.Singleton

@Singleton
class EventDeserializer {

    val mapper = ObjectMapper().registerModule(KotlinModule())

    fun deserialize(payload: String, action: Action): Event {
        return when (action) {
            Action.ADD -> mapper.readValue<ItemAddedEvent>(payload)
            // Add more cases here for different event types
            else -> throw IllegalArgumentException("Unknown event type: $action")
        }
    }
}