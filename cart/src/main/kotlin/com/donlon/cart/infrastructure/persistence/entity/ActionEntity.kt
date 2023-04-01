package com.donlon.cart.infrastructure.persistence.entity

import io.micronaut.serde.annotation.Serdeable

@Serdeable
enum class ActionEntity {
    ADD, REMOVE;
}