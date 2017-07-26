package com.lapismc.minecraft.yggdrasil

import com.lapismc.minecraft.yggdrasil.rest.*
import com.lapismc.minecraft.yggdrasil.rest.serialization.AuthenticationResponseDeserializer
import java.util.*

/**
 * Authentication service that connects to a RESTful web application.
 * @param baseUrl URL of the REST application handling authentication.
 */
class WebAuthenticationService(baseUrl: String) : AuthenticationService {
    companion object {
        /**
         * Base URL for the official authentication server.
         */
        private const val OFFICIAL_BASE_URL = "https://authserver.mojang.com"

        /**
         * Official Mojang authentication service.
         */
        fun official() = WebAuthenticationService(OFFICIAL_BASE_URL)
    }

    private val restService: RestService = RestService(baseUrl)

    /**
     * Attempts to authenticate with a user's credentials.
     * @param credentials User credentials for authentication.
     * @param clientToken Token used to uniquely identify the client.
     * @param agent Game to authenticate for.
     */
    override fun authenticate(credentials: Credentials, clientToken: UUID, agent: Agent) {
        val request = AuthenticationRequest(credentials, clientToken, agent)
        restService.post(request, AuthenticationResponseDeserializer())
    }

    /**
     * Renews the access token given during authentication.
     * This allows continued use of the token pair.
     * @param tokenPair Client and access token used during authentication.
     */
    override fun refresh(tokenPair: TokenPair) {
        val request = RefreshRequest(tokenPair)
        restService.post(request, AuthenticationResponseDeserializer())
    }

    /**
     * Checks whether an access token is still usable.
     * @param tokenPair Client and access token used during authentication.
     */
    override fun validate(tokenPair: TokenPair) {
        val request = ValidateRequest(tokenPair)
        restService.post(request)
    }

    /**
     * Invalidates all access tokens.
     * Effectively requires all clients using the player's credentials to re-authenticate.
     * @param credentials User credentials.
     */
    override fun signout(credentials: Credentials) {
        val request = SignoutRequest(credentials)
        restService.post(request)
    }

    /**
     * Makes an access token unusable.
     * The user must re-authenticate to use the same client.
     * @param tokenPair Client and access token used during authentication.
     */
    override fun invalidate(tokenPair: TokenPair) {
        val request = InvalidateRequest(tokenPair)
        restService.post(request)
    }
}