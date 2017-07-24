package com.lapismc.minecraft.yggdrasil.rest

import com.lapismc.minecraft.yggdrasil.TokenPair

/**
 * Shared class for all requests that use the client/access token pair.
 * @param tokenPair Client and access token used during authentication.
 */
abstract class TokenRequest(val tokenPair: TokenPair) : Request()