package com.donlon.cartcommand.controller

import com.donlon.cartcommand.service.model.CartCommand
import com.donlon.cartcommand.service.model.Item
import com.donlon.cartcommand.service.model.event.Action
import io.micronaut.serde.ObjectMapper
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.restassured.specification.RequestSpecification
import org.hamcrest.core.IsNull.notNullValue
import org.junit.jupiter.api.Test


@MicronautTest(transactional = false)
class CartCommandControllerTest {

    @Test
    fun `create cart should return cart id`(spec: RequestSpecification, objectMapper: ObjectMapper) {
        val cartCommand = objectMapper.writeValueAsString(
            CartCommand(
                Item("item-1", 1),
                Action.ADD
            )
        )

        spec
            .contentType("application/json")
            .body(cartCommand)
            .`when`()
            .post("/carts")
            .then()
            .statusCode(200)
            .body("cartId", notNullValue())
    }

    @Test
    fun `update cart with ADD should return OK`(spec: RequestSpecification, objectMapper: ObjectMapper) {
        val cartId = "testCartId2"
        val cartCommand = objectMapper.writeValueAsString(
            CartCommand(
                Item("item-1", 1),
                Action.ADD
            )
        )

        spec
            .contentType("application/json")
            .body(cartCommand)
            .`when`()
            .post("/carts/$cartId")
            .then()
            .statusCode(200)
    }

    @Test
    fun `update cart with REMOVE should return OK`(spec: RequestSpecification, objectMapper: ObjectMapper) {
        val cartId = "testCartId2"
        val cartCommand = objectMapper.writeValueAsString(
            CartCommand(
                Item("item-1", 1),
                Action.REMOVE
            )
        )

        spec
            .contentType("application/json")
            .body(cartCommand)
            .`when`()
            .post("/carts/$cartId")
            .then()
            .statusCode(200)
    }

}