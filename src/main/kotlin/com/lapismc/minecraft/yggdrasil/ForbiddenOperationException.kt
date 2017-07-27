package com.lapismc.minecraft.yggdrasil

/**
 * Error for when an operation occurred that is not allowed.
 */
class ForbiddenOperationException : Exception {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}