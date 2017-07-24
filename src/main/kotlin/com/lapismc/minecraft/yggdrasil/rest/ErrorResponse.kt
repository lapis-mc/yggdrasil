package com.lapismc.minecraft.yggdrasil.rest

/**
 * Response received for all possible errors.
 * @param error Error name and type.
 * @param message Brief text describing the problem.
 * @param cause Error name and type of the issue that caused the original problem.
 */
class ErrorResponse(val error: String, val message: String, val cause: String? = null) : Response() {
    /**
     * Indicates whether the request was successful.
     */
    override val successful = false
}