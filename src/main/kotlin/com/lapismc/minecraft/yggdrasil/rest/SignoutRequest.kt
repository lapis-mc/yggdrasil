package com.lapismc.minecraft.yggdrasil.rest

import com.lapismc.minecraft.yggdrasil.Credentials
import com.lapismc.minecraft.yggdrasil.rest.serialization.Serializer

/**
 * Request for invalidating all client/access token pairs.
 * @param credentials User credentials for authentication.
 */
class SignoutRequest(val credentials: Credentials) : Request() {
    /**
     * Sub-path from the base URL that the request is sent to.
     */
    override val endpoint = "/signout"

    /**
     * Serializes the request.
     */
    override fun <T> serialize(serializer: Serializer<T>): T = TODO()
}