package com.donlon.cartcommand.service.model.event

import io.micronaut.serde.annotation.Serdeable

@Serdeable
enum class Action {
    ADD, REMOVE;
}