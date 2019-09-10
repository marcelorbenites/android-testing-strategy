package com.droidcon.conference

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

class OkHttpConferenceGateway(
    private val baseUrl: String,
    private val httpClient: OkHttpClient
) : ConferenceGateway {
    override fun getConference(): Conference {
        val request = Request.Builder()
            .url("${baseUrl}conference")
            .get()
            .build()

        val response = httpClient.newCall(request).execute()

        if (!response.isSuccessful) {
            throw IOException()
        } else {
            return parseConference(response.body()!!.string())
        }
    }

    private fun parseConference(json: String): Conference {
        val conferenceJson = JSONObject(json)
        return Conference(conferenceJson.getString("id"), conferenceJson.getString("name"))
    }
}
