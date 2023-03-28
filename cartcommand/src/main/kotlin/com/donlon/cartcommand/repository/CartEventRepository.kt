package com.donlon.cartcommand.repository

import com.donlon.cartcommand.service.model.event.CartEvent
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.reactive.ReactorPageableRepository
import reactor.core.publisher.Mono

@R2dbcRepository(dialect = Dialect.MYSQL)
abstract class CartEventRepository : ReactorPageableRepository<CartEvent, Long> {

    abstract fun save(cartEvent: CartEvent): Mono<CartEvent>

}