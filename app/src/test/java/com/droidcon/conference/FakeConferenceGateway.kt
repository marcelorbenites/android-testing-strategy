package com.droidcon.conference

class FakeConferenceGateway(private val conference: Conference? = null) : ConferenceGateway {
    override fun getConference(): Conference = conference!!
}
