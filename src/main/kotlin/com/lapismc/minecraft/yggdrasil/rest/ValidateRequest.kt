package com.lapismc.minecraft.yggdrasil.rest

import com.lapismc.minecraft.yggdrasil.TokenPair

/**
 * Request for checking whether a client/access token pair are still usable.
 * @param tokenPair Client and access token used during authentication.
 */
class ValidateRequest(tokenPair: TokenPair) : TokenRequest(tokenPair) {
    /**
     * Sub-path from the base URL that the request is sent to.
     */
    override val endpoint = "/validate"
}