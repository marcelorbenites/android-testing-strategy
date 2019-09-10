package com.droidcon

import com.droidcon.conference.Conference
import com.droidcon.conference.ConferenceGateway
import com.droidcon.conference.ConferenceStateMachine
import com.droidcon.gateway.GatewayError
import com.droidcon.gateway.GatewayErrorFactory
import com.droidcon.state.Dispatcher
import com.droidcon.state.StateMachine

class DroidconApplication private constructor(
    lazyDispatcherFactory: Lazy<DispatcherFactory>,
    lazyConferenceGateway: Lazy<ConferenceGateway>
) : DependencyManager {

    override val conferenceStateMachine: StateMachine<Conference, GatewayError> by lazy {
        val machine = ConferenceStateMachine(
            lazyConferenceGateway.value,
            lazyDispatcherFactory.value.createSerialDispatcher("Conference"),
            GatewayErrorFactory()
        )
        machine
    }

    override val mainDispatcher: Dispatcher by lazy {
        lazyDispatcherFactory.value.createMainDispatcher()
    }

    private fun start() {
        conferenceStateMachine.start()
    }

    class Builder {

        private var lazyDispatcherFactory: Lazy<DispatcherFactory>? = null
        private var lazyConferenceGateway: Lazy<ConferenceGateway>? = null

        fun registerDispatcherFactory(lazyDispatcherFactory: Lazy<DispatcherFactory>): Builder {
            this.lazyDispatcherFactory = lazyDispatcherFactory
            return this
        }

        fun registerConferenceGateway(lazyConferenceGateway: Lazy<ConferenceGateway>): Builder {
            this.lazyConferenceGateway = lazyConferenceGateway
            return this
        }

        fun start(): DroidconApplication {
            val application =
                DroidconApplication(lazyDispatcherFactory!!, lazyConferenceGateway!!)
            application.start()
            return application
        }
    }
}
