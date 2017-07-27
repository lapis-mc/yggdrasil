package com.lapismc.minecraft.yggdrasil.rest

import com.lapismc.minecraft.yggdrasil.ForbiddenOperationException

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

    /**
     * Converts the error to a logical exception.
     * @return Exception containing the error information.
     */
    fun toException(): Exception {
        val klass = errorNameToExceptionClass(error)
        return if(cause == null) {
            klass.getConstructor(String::class.java).newInstance(message)
        } else {
            val causedBy = errorNameToExceptionClass(cause).newInstance()
            klass.getConstructor(String::class.java, Exception::class.java).newInstance(message, causedBy)
        }
    }

    /**
     * Retrieves the class for the corresponding error string.
     * @param error Error type.
     * @return Exception class type.
     */
    private fun errorNameToExceptionClass(error: String) = when(error) {
        // TODO: Is it possible to use the Kotlin class instead of the Java class?
        "ForbiddenOperationException" -> ForbiddenOperationException::class.java
        "IllegalArgumentException"    -> IllegalArgumentException::class.java
        else -> Exception::class.java
    }
}