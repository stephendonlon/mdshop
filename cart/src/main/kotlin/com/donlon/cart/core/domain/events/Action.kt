package com.donlon.cart.core.domain.events

import io.micronaut.serde.annotation.Serdeable

@Serdeable
enum class Action {
    ADD, REMOVE;
}