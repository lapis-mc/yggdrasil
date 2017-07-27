package com.lapismc.minecraft.yggdrasil.rest

import com.lapismc.minecraft.yggdrasil.Agent
import com.lapismc.minecraft.yggdrasil.Credentials
import com.lapismc.minecraft.yggdrasil.rest.serialization.Serializer
import java.util.UUID

/**
 * Request for authenticating a user.
 * @param credentials User credentials for authentication.
 * @param clientToken Token used to uniquely identify the client.
 * @param agent Game to authenticate for.
 */
class AuthenticationRequest(val credentials: Credentials, val clientToken: UUID, val agent: Agent) : Request() {
    /**
     * Sub-path from the base URL that the request is sent to.
     */
    override val endpoint = "/authenticate"

    /**
     * Serializes the request.
     * @param serializer Serializer used to transform the request.
     * @return Serialized data.
     */
    override fun <T> serialize(serializer: Serializer<T>): T {
        return serializer.serializeAuthenticationRequest(this)
    }
}