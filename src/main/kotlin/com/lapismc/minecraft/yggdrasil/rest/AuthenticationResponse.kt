package com.lapismc.minecraft.yggdrasil.rest

import com.lapismc.minecraft.yggdrasil.ProfileList
import com.lapismc.minecraft.yggdrasil.TokenPair

/**
 * Response received when an authentication request is successful.
 * @param tokenPair Client and access token pair to use in place of credentials.
 * @param profiles Set of profiles the player has for the selected game.
 */
class AuthenticationResponse(val tokenPair: TokenPair, val profiles: ProfileList) : Response() {
    /**
     * Indicates whether the request was successful.
     */
    override val successful = true
}