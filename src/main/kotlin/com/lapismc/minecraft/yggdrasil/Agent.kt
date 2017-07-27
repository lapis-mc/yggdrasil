package com.lapismc.minecraft.yggdrasil

/**
 * Short bit of information describing the game client.
 * @param name Name of the game (Minecraft).
 * @param version Version for the specified game.
 */
data class Agent(val name: String, val version: Int) {
    companion object {
        /**
         * Default agent for Minecraft.
         * @return Minecraft agent.
         */
        fun minecraft() = Agent("Minecraft", 1)
    }
}