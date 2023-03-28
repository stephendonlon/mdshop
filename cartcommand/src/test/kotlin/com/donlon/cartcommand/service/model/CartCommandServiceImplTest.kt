package com.donlon.cartcommand.service.model

import com.donlon.cartcommand.kafka.CartEventProducer
import com.donlon.cartcommand.repository.CartEventRepository
import com.donlon.cartcommand.service.CartCommandServiceImpl
import com.donlon.cartcommand.service.model.event.Action
import com.donlon.cartcommand.service.model.event.CartEvent
import com.donlon.cartcommand.service.uuid.UUIDGenerator
import io.micronaut.serde.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import java.util.*


private const val CART_1 = "cart-1"
private const val ITEM_1 = "item-1"
private const val ITEM_2 = "item-2"

class CartCommandServiceImplTest {

    private val cartEventRepository: CartEventRepository = mock()
    private val objectMapper = ObjectMapper.getDefault()
    private val uuidGenerator: UUIDGenerator = mock()
    private val cartEventProducer: CartEventProducer = mock()
    private val cartCommandService =
        CartCommandServiceImpl(cartEventRepository, objectMapper, uuidGenerator, cartEventProducer)

    @Test
    fun `test addToNewCart`() {
        val item = Item(ITEM_1, 2)
        val command = CartCommand(item = item, Action.ADD)
        whenever(uuidGenerator.generate()).thenReturn(CART_1);
        val cartEvent = CartEvent(
            cartId = CART_1,
            payload = objectMapper.writeValueAsString(item),
            createdTime = LocalDateTime.now()
        )

        whenever(cartEventRepository.save(any())).thenReturn(Mono.just(cartEvent))
        whenever(cartEventProducer.sendCartEvent(any(), any())).thenReturn(Mono.just(cartEvent))

        val result = cartCommandService.addToNewCart(command).block()

        val savedItem = objectMapper.readValue(result?.payload, Item::class.java)
        assertEquals(item.productId, savedItem.productId)
        assertEquals(item.quantity, savedItem.quantity)
    }

    @Test
    fun `test update`() {
        val item = Item(ITEM_2, 3)
        val command = CartCommand(item = item, Action.ADD)
        val cartId = UUID.randomUUID().toString()
        val cartEvent = CartEvent(
            cartId = cartId,
            payload = objectMapper.writeValueAsString(item),
            createdTime = LocalDateTime.now()
        )

        whenever(cartEventRepository.save(any())).thenReturn(Mono.just(cartEvent))
        whenever(cartEventProducer.sendCartEvent(any(), any())).thenReturn(Mono.just(cartEvent))

        val result = cartCommandService.update(command, cartId).block()

        val savedItem = objectMapper.readValue(result?.payload, Item::class.java)
        assertEquals(cartId, result?.cartId)
        assertEquals(item.productId, savedItem.productId)
        assertEquals(item.quantity, savedItem.quantity)
    }
}