package com.donlon.cartcommand.kafka

import com.donlon.cartcommand.service.model.CartCommand
import com.donlon.cartcommand.service.model.Item
import com.donlon.cartcommand.service.model.event.Action
import com.donlon.cartcommand.service.model.event.CartEvent
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.OffsetReset.EARLIEST
import io.micronaut.configuration.kafka.annotation.Topic
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.restassured.specification.RequestSpecification
import jakarta.inject.Inject
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.awaitility.Awaitility.await
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import java.util.*
import java.util.concurrent.ConcurrentLinkedDeque
import java.util.concurrent.TimeUnit.SECONDS


@MicronautTest(transactional = false)
@TestInstance(PER_CLASS)
class CartCommandKafkaIT {

    private lateinit var kafkaConsumer: KafkaConsumer<String, String>

    @Inject
    lateinit var cartListener: CartListener

    companion object {
        val received: MutableCollection<CartEvent> = ConcurrentLinkedDeque()
    }

    @BeforeEach
    fun setup() {
        received.clear()
    }

    @Test
    fun `test create cart and check messages in Kafka`(spec: RequestSpecification) {
        val item = Item("item-1", 1)
        val command = CartCommand(item, Action.ADD)

        val cartId = spec.given()
            .body(command)
            .contentType(MediaType.APPLICATION_JSON)
            .`when`()
            .post("/carts")
            .then()
            .statusCode(HttpStatus.OK.code)
            .extract()
            .jsonPath()
            .getString("cartId")

        await().atMost(5, SECONDS).until { !received.isEmpty() }

        assertEquals(cartId, received.first().cartId)
    }
}

@KafkaListener(offsetReset = EARLIEST)
class CartListener {

    @Topic("cart-events")
    fun consumeCartEvents(cartEvent: CartEvent) {
        CartCommandKafkaIT.received.add(cartEvent)
    }
}