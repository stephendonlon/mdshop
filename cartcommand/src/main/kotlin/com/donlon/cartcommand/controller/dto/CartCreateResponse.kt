package com.donlon.cartcommand.controller.dto

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class CartCreateResponse(val cartId: String)
