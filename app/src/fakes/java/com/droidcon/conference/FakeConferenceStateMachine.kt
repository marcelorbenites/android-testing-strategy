package com.droidcon.conference

import com.droidcon.gateway.GatewayError
import com.droidcon.state.ErrorFactory
import com.droidcon.state.State
import com.droidcon.state.StateMachine

class FakeConferenceStateMachine(
    errorFactory: ErrorFactory<GatewayError>,
    currentState: State<Conference, GatewayError>
) : StateMachine<Conference, GatewayError>(errorFactory, currentState) {
    override fun start() {
    }
}
