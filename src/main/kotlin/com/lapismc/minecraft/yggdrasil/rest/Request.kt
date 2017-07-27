package com.lapismc.minecraft.yggdrasil.rest

import com.lapismc.minecraft.yggdrasil.rest.serialization.Serializer

/**
 * Base class for all REST API requests.
 */
abstract class Request {
    /**
     * Sub-path from the base URL that the request is sent to.
     */
    abstract val endpoint: String

    /**
     * Uses strategy pattern to serialize the request data.
     */
    abstract fun <T> serialize(serializer: Serializer<T>): T
}