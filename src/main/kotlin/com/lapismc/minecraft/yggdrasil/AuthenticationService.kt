package com.lapismc.minecraft.yggdrasil

import java.util.UUID

/**
 * Service providing a means to authenticate a user's account.
 */
interface AuthenticationService {
    /**
     * Attempts to authenticate with a user's credentials.
     * @param credentials User credentials for authentication.
     * @param clientToken Token used to uniquely identify the client.
     * @param agent Game to authenticate for.
     */
    fun authenticate(credentials: Credentials, clientToken: UUID, agent: Agent = Agent.minecraft())

    /**
     * Renews the access token given during authentication.
     * This allows continued use of the token pair.
     * @param tokenPair Client and access token used during authentication.
     */
    fun refresh(tokenPair: TokenPair)

    /**
     * Checks whether an access token is still usable.
     * @param tokenPair Client and access token used during authentication.
     */
    fun validate(tokenPair: TokenPair)

    /**
     * Invalidates all access tokens.
     * Effectively requires all clients using the player's credentials to re-authenticate.
     * @param credentials User credentials.
     */
    fun signout(credentials: Credentials)

    /**
     * Makes an access token unusable.
     * The user must re-authenticate to use the same client.
     * @param tokenPair Client and access token used during authentication.
     */
    fun invalidate(tokenPair: TokenPair)
}