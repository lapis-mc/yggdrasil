package com.lapismc.minecraft.yggdrasil.rest

/**
 * Base class for all responses that could be received.
 */
abstract class Response {
    /**
     * Indicates whether the request was successful.
     */
    abstract val successful: Boolean
}