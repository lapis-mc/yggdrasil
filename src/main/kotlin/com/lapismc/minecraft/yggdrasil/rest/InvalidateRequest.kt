package com.lapismc.minecraft.yggdrasil.rest

import com.lapismc.minecraft.yggdrasil.TokenPair

/**
 * Request for forcing a client/access token pair to become unusable.
 * @param tokenPair Client and access token used during authentication.
 */
class InvalidateRequest(tokenPair: TokenPair) : TokenRequest(tokenPair) {
    /**
     * Sub-path from the base URL that the request is sent to.
     */
    override val endpoint = "/invalidate"
}