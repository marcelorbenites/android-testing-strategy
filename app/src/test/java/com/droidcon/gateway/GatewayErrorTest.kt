package com.droidcon.gateway

import org.json.JSONException
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException

class GatewayErrorTest {

    @Test
    fun `Given an IO cause When is network checked Then return true`() {
        val conferenceError = GatewayError(IOException())

        assertEquals(true, conferenceError.network)
    }

    @Test
    fun `Given a non-IO cause When is network checked Then return false`() {
        val conferenceError =
            GatewayError(JSONException("networkError parsing json"))
        assertEquals(false, conferenceError.network)
    }

    @Test
    fun `Given a null cause When is network checked Then return false`() {
        val conferenceError = GatewayError(null)

        assertEquals(false, conferenceError.network)
    }


}
