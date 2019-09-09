package com.droidcon

import com.droidcon.conference.*
import com.droidcon.gateway.GatewayError
import com.droidcon.gateway.GatewayErrorFactory
import com.droidcon.state.StateMachine

class DroidconApplication private constructor(
    lazyDispatcherFactory: Lazy<DispatcherFactory>,
    lazyConferenceGateway: Lazy<ConferenceGateway>
) : DependencyManager {

    override val conferenceViewModel: ConferenceViewModel by lazy {
        ConferenceViewModel()
    }

    private val conferenceStateMachine: StateMachine<Conference, GatewayError> by lazy {
        val machine = ConferenceStateMachine(
            lazyConferenceGateway.value,
            lazyDispatcherFactory.value.createSerialDispatcher("Conference"),
            GatewayErrorFactory()
        )
        machine.addStateChangedListener(
            ConferencePresenter(
                lazyDispatcherFactory.value.createMainDispatcher(),
                conferenceViewModel
            )
        )
        machine
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
