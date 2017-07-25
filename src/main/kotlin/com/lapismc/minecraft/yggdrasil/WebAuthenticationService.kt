package com.lapismc.minecraft.yggdrasil

import java.util.*

/**
 * Authentication service that connects to a RESTful web application.
 * @param baseUrl URL of the REST application handling authentication.
 */
class WebAuthenticationService(private val baseUrl: String) : AuthenticationService {
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

    /**
     * Attempts to authenticate with a user's credentials.
     * @param credentials User credentials for authentication.
     * @param clientToken Token used to uniquely identify the client.
     * @param agent Game to authenticate for.
     */
    override fun authenticate(credentials: Credentials, clientToken: UUID, agent: Agent) = TODO()

    /**
     * Renews the access token given during authentication.
     * This allows continued use of the token pair.
     * @param tokenPair Client and access token used during authentication.
     */
    override fun refresh(tokenPair: TokenPair) = TODO()

    /**
     * Checks whether an access token is still usable.
     * @param tokenPair Client and access token used during authentication.
     */
    override fun validate(tokenPair: TokenPair) = TODO()

    /**
     * Invalidates all access tokens.
     * Effectively requires all clients using the player's credentials to re-authenticate.
     * @param credentials User credentials.
     */
    override fun signout(credentials: Credentials) = TODO()

    /**
     * Makes an access token unusable.
     * The user must re-authenticate to use the same client.
     * @param tokenPair Client and access token used during authentication.
     */
    override fun invalidate(tokenPair: TokenPair) = TODO()
}