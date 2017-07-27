package com.lapismc.minecraft.yggdrasil

import java.security.MessageDigest
import javax.xml.bind.DatatypeConverter.printHexBinary

/**
 * User information for authentication.
 * @param username Minecraft username or Mojang account.
 * @param password Password associated with the username.
 */
data class Credentials(val username: String, val password: String) {
    /**
     * Generates a safe string representation of the credentials.
     * The password is hashed to allow verification, but the original can't be obtained.
     * @return Safe string containing the credentials.
     */
    override fun toString(): String {
        val passwordHash = sha256Digest(password)
        return "Credentials(username=$username, passwordHash=$passwordHash)"
    }

    companion object {
        /**
         * Computes the SHA-256 hash of a string.
         * @param value String to compute the hash of.
         * @return Hash of the string in hexadecimal.
         */
        private fun sha256Digest(value: String): String {
            val digest = MessageDigest.getInstance("SHA-256")
            val hashBytes = digest.digest(value.toByteArray())
            return printHexBinary(hashBytes).take(16).toLowerCase() + "..."
        }
    }
}