package com.lapismc.minecraft.yggdrasil.rest.serialization

import com.lapismc.minecraft.yggdrasil.rest.*

/**
 * Serializer for generating JSON data from request objects.
 */
class JsonSerializer : Serializer<String> {
    /**
     * Serializes an authentication request.
     * @param request Request to serialize.
     * @return Serialized data.
     */
    override fun serializeAuthenticationRequest(request: AuthenticationRequest): String = TODO()

    /**
     * Serializes a token request.
     * @param request Request to serialize.
     * @return Serialized data.
     */
    override fun serializeTokenRequest(request: TokenRequest): String = TODO()

    /**
     * Serializes a signout request.
     * @param request Request to serialize.
     * @return Serialized data.
     */
    override fun serializeSignoutRequest(request: SignoutRequest): String = TODO()
}