package com.donlon.cartcommand.service.model.event

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import java.time.LocalDateTime
import java.util.*

@Serdeable
@MappedEntity
data class CartEvent(
    @field: Id
    @field:GeneratedValue(GeneratedValue.Type.IDENTITY)
    var id: Long? = null,
    val cartId: String,
    val payload: String,
    val action: Action,
    val createdTime: LocalDateTime,
)
