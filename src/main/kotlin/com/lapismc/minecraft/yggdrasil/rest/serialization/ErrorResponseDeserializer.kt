package com.lapismc.minecraft.yggdrasil.rest.serialization

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.salomonbrys.kotson.*
import com.google.gson.GsonBuilder
import com.lapismc.minecraft.yggdrasil.rest.ErrorResponse

class ErrorResponseDeserializer : ResponseDeserializable<ErrorResponse> {
    /**
     * Read error response information.
     * @param content Serialized JSON data to deserialize.
     * @return Constructed error response.
     */
    override fun deserialize(content: String): ErrorResponse {
        val gson = GsonBuilder()
                .registerTypeAdapter<ErrorResponse> {
                    deserialize { readErrorResponse(it) }
                    serialize { jsonNull } // Empty, unused serializer to make Kotson happy.
                }
                .create()
        return gson.fromJson<ErrorResponse>(content)
    }

    /**
     * Processes the elements of the error response.
     * @param deserializer Deserialization information.
     * @return Parsed error response.
     */
    private fun readErrorResponse(deserializer: DeserializerArg): ErrorResponse {
        /**
         * Error responses look like this:
         * {
         *   "error": "Short error name, typically exception class",
         *   "errorMessage": "Description of the problem",
         *   "cause": "Optional cause of the error"
         * }
         */
        val root    = deserializer.json.asJsonObject
        val error   = root["error"].string
        val message = root["errorMessage"].string
        val cause   = if(root.has("cause")) root["cause"].string else null
        return ErrorResponse(error, message, cause)
    }
}