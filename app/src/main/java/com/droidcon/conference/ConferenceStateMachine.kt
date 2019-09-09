package com.droidcon.conference

import com.droidcon.state.Dispatcher
import com.droidcon.gateway.GatewayError
import com.droidcon.state.ErrorFactory
import com.droidcon.state.StateMachine

class ConferenceStateMachine(
    private val conferenceGateway: ConferenceGateway,
    dispatcher: Dispatcher,
    errorFactory: ErrorFactory<GatewayError>
) : StateMachine<Conference, GatewayError>(errorFactory, dispatcher) {

    override fun start() {
        loadConference()
    }

    private fun loadConference() {
        move { currentState ->
            conferenceGateway.getConference()
        }
    }
}
