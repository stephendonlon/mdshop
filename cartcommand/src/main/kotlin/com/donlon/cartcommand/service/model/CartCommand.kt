package com.donlon.cartcommand.service.model

import com.donlon.cartcommand.service.model.event.Action
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class CartCommand(var item: Item, var command: Action)
