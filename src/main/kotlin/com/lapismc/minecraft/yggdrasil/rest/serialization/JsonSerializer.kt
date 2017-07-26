package com.lapismc.minecraft.yggdrasil.rest.serialization

import com.github.salomonbrys.kotson.jsonObject
import com.github.salomonbrys.kotson.string
import com.google.gson.JsonObject
import com.lapismc.minecraft.yggdrasil.Agent
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
    override fun serializeAuthenticationRequest(request: AuthenticationRequest): String {
        return createAuthenticationRequestJson(request).string
    }

    /**
     * Serializes a token request.
     * @param request Request to serialize.
     * @return Serialized data.
     */
    override fun serializeTokenRequest(request: TokenRequest): String {
        return createTokenRequestJson(request).string
    }

    /**
     * Serializes a signout request.
     * @param request Request to serialize.
     * @return Serialized data.
     */
    override fun serializeSignoutRequest(request: SignoutRequest): String {
        return createSignoutRequestJson(request).string
    }

    /**
     * Creates a JSON object containing the authentication request.
     * @param request Authentication request information.
     * @return JSON object containing the authentication request.
     */
    private fun createAuthenticationRequestJson(request: AuthenticationRequest): JsonObject {
        return jsonObject(
                "agent"       to createAgentJson(request.agent),
                "username"    to request.credentials.username,
                "password"    to request.credentials.password,
                "clientToken" to request.clientToken
        )
    }

    /**
     * Creates a JSON object containing agent information.
     * @param agent Agent information.
     * @return JSON object containing the agent information.
     */
    private fun createAgentJson(agent: Agent): JsonObject {
        return jsonObject(
                "name"    to agent.name,
                "version" to agent.version
        )
    }

    /**
     * Creates a JSON object containing a token pair request.
     * @param request Token request information.
     * @return JSON object containing the token pair information.
     */
    private fun createTokenRequestJson(request: TokenRequest): JsonObject {
        return jsonObject(
                "accessToken" to request.tokenPair.accessToken,
                "clientToken" to request.tokenPair.clientToken
        )
    }

    /**
     * Creates a JSON object containing a signout request.
     * @param request Signout request information.
     * @return JSON object containing the signout information.
     */
    private fun createSignoutRequestJson(request: SignoutRequest): JsonObject {
        return jsonObject(
                "username" to request.credentials.username,
                "password" to request.credentials.password
        )
    }
}