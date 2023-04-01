package com.donlon.cart.infrastructure.persistence.repositories

import com.donlon.cart.infrastructure.persistence.entity.CartEventEntity
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.reactive.ReactorPageableRepository
import reactor.core.publisher.Mono

@R2dbcRepository(dialect = Dialect.MYSQL)
abstract class CartEventRepository : ReactorPageableRepository<CartEventEntity, Long> {

    abstract fun save(cartEvent: CartEventEntity): Mono<CartEventEntity>
}