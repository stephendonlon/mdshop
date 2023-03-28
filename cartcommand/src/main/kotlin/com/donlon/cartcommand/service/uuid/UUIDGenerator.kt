package com.donlon.cartcommand.service.uuid

import jakarta.inject.Singleton

interface UUIDGenerator {
    fun generate(): String
}

@Singleton
class UUIDGeneratorImpl : UUIDGenerator {
    override fun generate(): String {
        return java.util.UUID.randomUUID().toString()
    }
}