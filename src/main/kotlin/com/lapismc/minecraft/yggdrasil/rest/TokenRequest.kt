package com.lapismc.minecraft.yggdrasil.rest

import com.lapismc.minecraft.yggdrasil.TokenPair
import com.lapismc.minecraft.yggdrasil.rest.serialization.Serializer

/**
 * Shared class for all requests that use the client/access token pair.
 * @param tokenPair Client and access token used during authentication.
 */
abstract class TokenRequest(val tokenPair: TokenPair) : Request() {
    /**
     * Serializes the request.
     * @param serializer Serializer used to transform the request.
     * @return Serialized data.
     */
    override fun <T> serialize(serializer: Serializer<T>): T {
        return serializer.serializeTokenRequest(this)
    }
}