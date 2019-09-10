package com.droidcon

import com.droidcon.conference.Conference
import com.droidcon.gateway.GatewayError
import com.droidcon.state.Dispatcher
import com.droidcon.state.StateMachine

class FakeDependencyManager(
    override val conferenceStateMachine: StateMachine<Conference, GatewayError>,
    override val mainDispatcher: Dispatcher
) : DependencyManager
