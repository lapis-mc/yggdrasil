package com.lapismc.minecraft.yggdrasil.rest.serialization

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.salomonbrys.kotson.*
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.lapismc.minecraft.yggdrasil.Profile
import com.lapismc.minecraft.yggdrasil.ProfileList
import com.lapismc.minecraft.yggdrasil.TokenPair
import com.lapismc.minecraft.yggdrasil.rest.AuthenticationResponse
import java.io.Reader
import java.util.UUID

class AuthenticationResponseDeserializer : ResponseDeserializable<AuthenticationResponse> {
    /**
     * Read authentication response information.
     * @param reader JSON reader used to get error information.
     * @return Constructed authentication response.
     */
    override fun deserialize(reader: Reader): AuthenticationResponse {
        val gson = GsonBuilder()
                .registerTypeAdapter<AuthenticationResponse> {
                    deserialize { readAuthenticationResponse(it) }
                    serialize { jsonNull } // Empty, unused serializer to make Kotson happy.
                }
                .registerTypeAdapter<Profile> {
                    deserialize { readProfileBlock(it) }
                    serialize { jsonNull } // Empty, unused serializer.
                }
                .create()
        return gson.fromJson<AuthenticationResponse>(reader)
    }

    /**
     * Processes the elements of the authentication response.
     * @param deserializer Deserialization information.
     * @return Parsed authentication response.
     */
    private fun readAuthenticationResponse(deserializer: DeserializerArg): AuthenticationResponse {
        /**
         * Authentication responses look like this:
         * {
         *   "accessToken": "UUID of access token",
         *   "clientToken": "UUID of client token",
         *   "availableProfiles": [ ],
         *   "selectedProfile": { }
         * }
         */
        val root = deserializer.json.asJsonObject
        val accessToken = deserializeUUID(root["accessToken"])
        val clientToken = deserializeUUID(root["clientToken"])
        val tokenPair   = TokenPair(clientToken, accessToken)
        val profileList = readProfilesBlock(root["availableProfiles"], deserializer.context)
        val selectedProfile = deserializer.context.deserialize<Profile>(root["selectedProfile"])
        val profiles = ProfileList(profileList, selectedProfile.id)
        return AuthenticationResponse(tokenPair, profiles)
    }

    /**
     * Processes a collection of profiles.
     * @param element JSON element referencing the list of profiles.
     * @param context Gson serialization context.
     * @return Set of profiles from the JSON.
     */
    private fun readProfilesBlock(element: JsonElement, context: DeserializerArg.Context): List<Profile> {
        return context.deserialize<List<Profile>>(element)
    }

    /**
     * Processes data from a profile block.
     * @param deserializer Deserialization information.
     * @return Profile constructed from information in the JSON data.
     */
    private fun readProfileBlock(deserializer: DeserializerArg): Profile {
        /**
         * Profile block looks like this:
         * {
         *   "id": "uuid without dashes",
         *   "name": "player name",
         *   "legacy": false
         * }
         */
        val element = deserializer.json.asJsonObject
        val id      = deserializeUUID(element["id"])
        val name    = element["name"].string
        val legacy  = if(element.has("legacy")) element["legacy"].bool else false
        return Profile(id, name, legacy)
    }

    /**
     * Utility method for converting JSON UUID values to UUID instances.
     * @param element JSON element containing the UUID.
     * @return Extracted UUID value.
     */
    private fun deserializeUUID(element: JsonElement) = UUID.fromString(element.string)
}