//package com.donlon.cart.adapter.kafka.consumer
//
//import com.donlon.cart.adapter.kafka.testclient.TestCartEventProducer
//import com.donlon.cart.core.domain.events.CartEvent
//import com.donlon.cart.infrastructure.persistence.entity.CartEventEntity
//import com.donlon.cart.infrastructure.persistence.repositories.CartEventRepository
//import io.micronaut.test.extensions.junit5.annotation.MicronautTest
//import jakarta.inject.Inject
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.Test
//import reactor.core.publisher.Mono
//import reactor.test.StepVerifier
//import java.time.LocalDateTime
//
//
//@MicronautTest(transactional = false)
//class CartEventConsumerIT {
//    @Inject
//    lateinit var cartEventRepository: CartEventRepository
//
//    @Inject
//    lateinit var testCartEventProducer: TestCartEventProducer
//
//    @Test
//    fun `should consume CartEvent from Kafka and save in the database`() {
//        val eventId = 1L
//        val cartId = "cart-1"
//        val payload = "{\"product\": \"item-1\"}"
//
//        // Sent the CartEvent to Kafka
//        testCartEventProducer.sendCartEvent(cartId, CartEvent(eventId, cartId, payload, LocalDateTime.now()))
//
//        // Give the consumer some time to process the message
//        Thread.sleep(5000)
//
//        // Find the saved CartEvent in the database
//        val savedCartEvent: Mono<CartEventEntity> = cartEventRepository.findById(eventId)
//
//        // Verify the saved CartEvent
//        StepVerifier.create(savedCartEvent)
//            .assertNext {
//                assertEquals(cartId, it.cartId)
//                assertEquals(payload, it.payload)
//            }
//            .verifyComplete()
//    }
//}