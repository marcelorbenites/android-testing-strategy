package com.droidcon.gateway

import java.io.IOException

class GatewayError(cause: Throwable? = null) {
    val network: Boolean = cause != null && cause is IOException
}
