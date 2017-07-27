package com.lapismc.minecraft.yggdrasil

import com.github.kittinunf.result.Result
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
     * @return Result of the authentication.
     *  Upon success, an [AuthenticationResponse] is returned.
     *  If an error occurred, exception information about the problem is returned.
     */
    override fun authenticate(credentials: Credentials, clientToken: UUID, agent: Agent): Result<AuthenticationResponse, Exception> {
        val request = AuthenticationRequest(credentials, clientToken, agent)
        return restService.post(request, AuthenticationResponseDeserializer())
    }

    /**
     * Renews the access token given during authentication.
     * This allows continued use of the token pair.
     * @param tokenPair Client and access token used during authentication.
     * @return Result of the refresh.
     *  Upon success, an [AuthenticationResponse] is returned.
     *  If an error occurred, exception information about the problem is returned.
     */
    override fun refresh(tokenPair: TokenPair): Result<AuthenticationResponse, Exception> {
        val request = RefreshRequest(tokenPair)
        return restService.post(request, AuthenticationResponseDeserializer())
    }

    /**
     * Checks whether an access token is still usable.
     * @param tokenPair Client and access token used during authentication.
     * @return Result of the validation.
     *  Upon success, a boolean is returned indicating whether the token pair is valid.
     *  If an error occurred, exception information about the problem is returned.
     */
    override fun validate(tokenPair: TokenPair): Result<Boolean, Exception> {
        val request = ValidateRequest(tokenPair)
        return restService.post(request)
    }

    /**
     * Invalidates all access tokens.
     * Effectively requires all clients using the player's credentials to re-authenticate.
     * @param credentials User credentials.
     * @return Result of the signout.
     *  Upon success, a boolean is returned indicating whether the user was signed out.
     *  If an error occurred, exception information about the problem is returned.
     */
    override fun signout(credentials: Credentials): Result<Boolean, Exception> {
        val request = SignoutRequest(credentials)
        return restService.post(request)
    }

    /**
     * Makes an access token unusable.
     * The user must re-authenticate to use the same client.
     * @param tokenPair Client and access token used during authentication.
     * @return Result of the invalidation.
     *  Upon success, a boolean is returned indicating whether the token pair was invalidated.
     *  If an error occurred, exception information about the problem is returned.
     */
    override fun invalidate(tokenPair: TokenPair): Result<Boolean, Exception> {
        val request = InvalidateRequest(tokenPair)
        return restService.post(request)
    }
}