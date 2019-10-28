package com.droidcon.conference

import com.droidcon.dispatcher.FakeDispatcher
import com.droidcon.gateway.GatewayError
import com.droidcon.state.State
import io.mockk.mockk
import io.mockk.verifyOrder
import org.junit.Test

class ConferenceStateMachineTest {

    @Test
    fun `Given a registered conference When start is called Then should emit loaded state with conference`() {
        val conference = Conference("1", "Droidcon")
        val stateMachine = ConferenceStateMachine(
            FakeConferenceGateway(conference),
            FakeDispatcher(),
            FakeGatewayErrorFactory()
        )

        val listenerMock = mockk<(State<Conference, GatewayError>) -> Unit>(relaxed = true)
        stateMachine.addStateChangedListener(listenerMock)
        stateMachine.start()

        verifyOrder {
            listenerMock.invoke(State(State.Name.LOADING))
            listenerMock.invoke(State(State.Name.LOADED, conference))
        }
    }

    @Test
    fun `Given a registered conference When load conference is called Then should emit loaded state with conference`() {
        val conference = Conference("1", "Droidcon")
        val stateMachine = ConferenceStateMachine(
            FakeConferenceGateway(conference),
            FakeDispatcher(),
            FakeGatewayErrorFactory()
        )

        val listenerMock = mockk<(State<Conference, GatewayError>) -> Unit>(relaxed = true)
        stateMachine.addStateChangedListener(listenerMock)
        stateMachine.loadConference()

        verifyOrder {
            listenerMock.invoke(State(State.Name.LOADING))
            listenerMock.invoke(State(State.Name.LOADED, conference))
        }
    }
}
