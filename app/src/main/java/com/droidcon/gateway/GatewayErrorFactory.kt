package com.droidcon.gateway

import com.droidcon.state.ErrorFactory

class GatewayErrorFactory: ErrorFactory<GatewayError> {
    override fun create(throwable: Throwable) = GatewayError(throwable)
}
