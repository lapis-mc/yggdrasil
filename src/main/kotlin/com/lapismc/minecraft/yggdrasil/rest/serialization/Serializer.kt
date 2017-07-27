package com.lapismc.minecraft.yggdrasil.rest.serialization

import com.lapismc.minecraft.yggdrasil.rest.*

/**
 * Generic serializer for strategy pattern.
 * Request types accept this as an argument and call their corresponding method.
 * $T Output type that is serialized to.
 */
interface Serializer<out T> {
    /**
     * Serializes an authentication request.
     * @param request Request to serialize.
     * @return Serialized data.
     */
    fun serializeAuthenticationRequest(request: AuthenticationRequest): T

    /**
     * Serializes a token request.
     * @param request Request to serialize.
     * @return Serialized data.
     */
    fun serializeTokenRequest(request: TokenRequest): T

    /**
     * Serializes a signout request.
     * @param request Request to serialize.
     * @return Serialized data.
     */
    fun serializeSignoutRequest(request: SignoutRequest): T
}