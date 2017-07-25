package com.lapismc.minecraft.yggdrasil.rest

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.result.Result
import com.lapismc.minecraft.yggdrasil.rest.serialization.JsonSerializer

/**
 * Service for interacting with a JSON-based REST API.
 * @param baseUrl Web URL that all requests derive from.
 */
class RestService(private val baseUrl: String) {
    /**
     * Posts a request to the REST API and parses the response.
     * The request is serialized to JSON and the response is deserialized from JSON.
     * If an error occurs, then a failure result is returned and an exception is created with the error information.
     * @param request Request to send to the API.
     * @param responseDeserializer Object that deserializes the expected (no error) response.
     * @return Result of the request.
     *  If the request was successful, then the result will contain
     *  the deserialized object produced by [responseDeserializer].
     *  If the request failed, then the result will contain an exception containing the error information.
     */
    fun <T: Any> post(request: Request, responseDeserializer: ResponseDeserializable<T>): Result<T, Exception> {
        val url  = baseUrl + request.endpoint
        val body = request.serialize(JsonSerializer())
        val httpRequest = Fuel.post(url).header(Pair("Content-Type", "application/json")).body(body)
        val (_, _, result) = httpRequest.responseObject(responseDeserializer)
        return result
    }
}