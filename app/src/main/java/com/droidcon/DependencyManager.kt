package com.droidcon

import com.droidcon.conference.Conference
import com.droidcon.gateway.GatewayError
import com.droidcon.state.Dispatcher
import com.droidcon.state.StateMachine

interface DependencyManager {
    val conferenceStateMachine: StateMachine<Conference, GatewayError>
    val mainDispatcher: Dispatcher
}
