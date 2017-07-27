package com.lapismc.minecraft.yggdrasil

import java.util.UUID

/**
 * Player's profile information.
 * @param id Unique identifier for the profile.
 * @param name Player's in-game display name.
 * @param legacy Indicates whether this profile is a Minecraft account
 *  and not a Mojang account.
 */
data class Profile(val id: UUID, val name: String, val legacy: Boolean = false)