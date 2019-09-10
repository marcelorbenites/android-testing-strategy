package com.droidcon.conference

import com.droidcon.gateway.GatewayError
import com.droidcon.state.Dispatcher
import com.droidcon.state.ErrorFactory
import com.droidcon.state.StateMachine

class ConferenceStateMachine(
    private val conferenceGateway: ConferenceGateway,
    private val dispatcher: Dispatcher,
    errorFactory: ErrorFactory<GatewayError>
) : StateMachine<Conference, GatewayError>(errorFactory) {

    override fun start() {
        loadConference()
    }

    private fun loadConference() {
        dispatcher.dispatch({
            moveToLoading()
            moveToLoaded(conferenceGateway.getConference())
        }, { throwable ->
            moveToError(throwable)
        })
    }
}
