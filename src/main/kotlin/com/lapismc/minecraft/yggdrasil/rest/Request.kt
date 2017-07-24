package com.lapismc.minecraft.yggdrasil.rest

/**
 * Base class for all REST API requests.
 */
abstract class Request {
    /**
     * Sub-path from the base URL that the request is sent to.
     */
    abstract val endpoint: String
}