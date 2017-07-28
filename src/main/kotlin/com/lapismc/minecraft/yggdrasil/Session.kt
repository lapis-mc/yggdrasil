package com.lapismc.minecraft.yggdrasil

/**
 * Information about an authenticated user for a client.
 * @param profile Selected profile used for the session.
 * @param tokenPair Client and access token pair used for authentication.
 */
data class Session(val profile: Profile, val tokenPair: TokenPair)