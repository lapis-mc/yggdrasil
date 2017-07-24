package com.lapismc.minecraft.yggdrasil.rest

import com.lapismc.minecraft.yggdrasil.TokenPair

/**
 * Request for renewing the access token given during authentication.
 * @param tokenPair Client and access token used during authentication.
 */
class RefreshRequest(tokenPair: TokenPair) : TokenRequest(tokenPair) {
    /**
     * Sub-path from the base URL that the request is sent to.
     */
    override val endpoint = "/refresh"
}