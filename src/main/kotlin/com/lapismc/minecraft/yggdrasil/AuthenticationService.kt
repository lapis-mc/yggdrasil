package com.lapismc.minecraft.yggdrasil

import com.github.kittinunf.result.Result
import com.lapismc.minecraft.yggdrasil.rest.AuthenticationResponse
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
     * @return Result of the authentication.
     *  Upon success, an [AuthenticationResponse] is returned.
     *  If an error occurred, exception information about the problem is returned.
     */
    fun authenticate(credentials: Credentials, clientToken: UUID, agent: Agent = Agent.minecraft()): Result<AuthenticationResponse, Exception>

    /**
     * Renews the access token given during authentication.
     * This allows continued use of the token pair.
     * @param tokenPair Client and access token used during authentication.
     * @return Result of the refresh.
     *  Upon success, an [AuthenticationResponse] is returned.
     *  If an error occurred, exception information about the problem is returned.
     */
    fun refresh(tokenPair: TokenPair): Result<AuthenticationResponse, Exception>

    /**
     * Checks whether an access token is still usable.
     * @param tokenPair Client and access token used during authentication.
     * @return Result of the validation.
     *  Upon success, a boolean is returned indicating whether the token pair is valid.
     *  If an error occurred, exception information about the problem is returned.
     */
    fun validate(tokenPair: TokenPair): Result<Boolean, Exception>

    /**
     * Invalidates all access tokens.
     * Effectively requires all clients using the player's credentials to re-authenticate.
     * @param credentials User credentials.
     * @return Result of the signout.
     *  Upon success, a boolean is returned indicating whether the user was signed out.
     *  If an error occurred, exception information about the problem is returned.
     */
    fun signout(credentials: Credentials): Result<Boolean, Exception>

    /**
     * Makes an access token unusable.
     * The user must re-authenticate to use the same client.
     * @param tokenPair Client and access token used during authentication.
     * @return Result of the invalidation.
     *  Upon success, a boolean is returned indicating whether the token pair was invalidated.
     *  If an error occurred, exception information about the problem is returned.
     */
    fun invalidate(tokenPair: TokenPair): Result<Boolean, Exception>
}