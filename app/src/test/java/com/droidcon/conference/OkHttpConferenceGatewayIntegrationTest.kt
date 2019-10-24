package com.droidcon.conference

import androidx.test.ext.junit.runners.AndroidJUnit4
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OkHttpConferenceGatewayIntegrationTest {

    @Test
    fun `When conference is requested Then call db endpoint with GET method`() {
        val server = MockWebServer()
        server.start()
        val baseUrl = server.url("/").toString()

        val gateway = OkHttpConferenceGateway(
            baseUrl,
            OkHttpClient()
        )

        val json = """
            [{
              "id": "1",
              "name": "Droidcon"
            }]
        """.trimIndent()

        server.enqueue(MockResponse().setResponseCode(200).setBody(json))

        val conference = gateway.getConference()

        assertEquals(Conference("1", "Droidcon"), conference)

        val request = server.takeRequest()

        assertEquals("GET", request.method)
        assertEquals("/conferences", request.path)

        server.shutdown()
    }
}
