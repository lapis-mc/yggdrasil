package com.lapismc.minecraft.yggdrasil

import java.util.UUID

/**
 * Client and access tokens used to validate the player after authenticating.
 * @param clientToken Token representing the unique client.
 * @param accessToken Token provided by the authentication service.
 */
data class TokenPair(val clientToken: UUID, val accessToken: UUID)