package com.donlon.cartcommand.service.model

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class Item(val productId: String, val quantity: Int)
