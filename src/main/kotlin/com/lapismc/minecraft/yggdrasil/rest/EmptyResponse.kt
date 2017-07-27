package com.lapismc.minecraft.yggdrasil.rest

/**
 * An empty response meaning no content was received except that the request was successful.
 */
class EmptyResponse : Response() {
    /**
     * Indicates whether the request was successful.
     */
    override val successful = true
}