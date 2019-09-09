package com.droidcon.conference

import com.droidcon.gateway.GatewayError
import com.droidcon.state.ErrorFactory

class FakeGatewayErrorFactory(private val gatewayError: GatewayError? = null) :
    ErrorFactory<GatewayError> {
    override fun create(throwable: Throwable) = gatewayError!!
}
