package com.lapismc.minecraft.yggdrasil

import java.util.UUID

/**
 * Collection of profiles the player can choose from to play with.
 * @param available List of all profiles the player has.
 * @param selectedId ID of the profile the player is currently using.
 */
class ProfileList(val available: List<Profile>, val selectedId: UUID) {
    /**
     * Retrieves the player's currently selected profile.
     */
    val selected = available.find { it.id == selectedId }
}