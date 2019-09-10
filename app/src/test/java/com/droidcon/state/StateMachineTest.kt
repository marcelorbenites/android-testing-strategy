package com.droidcon.state

import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class StateMachineTest {

    @Test
    fun `Given an idle state When move is called Then current state should return loading with no value And no error`() {
        val stateMachine = object : StateMachine<TestValue, TestError>(
            TestErrorFactory()
        ) {
            override fun start() {
            }
        }
        val listenerMock = mockk<(State<TestValue, TestError>) -> Unit>(relaxed = true)
        stateMachine.addStateChangedListener(listenerMock)

        stateMachine.moveToLoading()

        verify {
            listenerMock.invoke(State(State.Name.LOADING, null, null))
        }
    }

    @Test
    fun `Given an idle state When move is called with value Then return loaded with value And no error`() {
        val stateMachine = object : StateMachine<TestValue, TestError>(
            TestErrorFactory()
        ) {
            override fun start() {
            }
        }
        val value = TestValue()
        val listenerMock = mockk<(State<TestValue, TestError>) -> Unit>(relaxed = true)
        stateMachine.addStateChangedListener(listenerMock)

        stateMachine.moveToLoaded(value)

        verify {
            listenerMock.invoke(State(State.Name.LOADED, value, null))
        }
    }

    @Test
    fun `Given an loaded state When move is called with error Then should return error state with error`() {
        val error = TestError()
        val stateMachine = object :
            StateMachine<TestValue, TestError>(
                TestErrorFactory(error)
            ) {
            override fun start() {
            }
        }
        val listenerMock = mockk<(State<TestValue, TestError>) -> Unit>(relaxed = true)
        stateMachine.addStateChangedListener(listenerMock)

        stateMachine.moveToError(IllegalStateException())

        verify {
            listenerMock.invoke(State(State.Name.ERROR, error = error))
        }
    }

    @Test
    fun `Given an idle state When two different listeners are registered and state is moved to loading Then should call both listeners`() {
        val stateMachine = object : StateMachine<TestValue, TestError>(
            TestErrorFactory()
        ) {
            override fun start() {
            }
        }
        val listenerMock = mockk<(State<TestValue, TestError>) -> Unit>(relaxed = true)

        val secondListenerMock = mockk<(State<TestValue, TestError>) -> Unit>(relaxed = true)
        stateMachine.addStateChangedListener(listenerMock)
        stateMachine.addStateChangedListener(secondListenerMock)
        stateMachine.moveToLoading()

        verify {
            listenerMock.invoke(State(State.Name.LOADING))
        }

        verify {
            secondListenerMock.invoke(State(State.Name.LOADING))
        }
    }

    @Test
    fun `Given an idle state When listener registered Then should return idle status`() {
        val stateMachine = object : StateMachine<TestValue, TestError>(
            TestErrorFactory()
        ) {
            override fun start() {
            }
        }
        val listenerMock = mockk<(State<TestValue, TestError>) -> Unit>(relaxed = true)

        stateMachine.addStateChangedListener(listenerMock)

        verify {
            listenerMock.invoke(State(State.Name.IDLE))
        }
    }

    @Test
    fun `Given an idle state and a registered listener When second listener registration Then should notify the second listener And should not notify the first listener a second time`() {
        val stateMachine = object : StateMachine<TestValue, TestError>(
            TestErrorFactory()
        ) {
            override fun start() {
            }
        }
        val listenerMock = mockk<(State<TestValue, TestError>) -> Unit>(relaxed = true)
        stateMachine.addStateChangedListener(listenerMock)

        val secondListenerMock = mockk<(State<TestValue, TestError>) -> Unit>(relaxed = true)
        stateMachine.addStateChangedListener(secondListenerMock)

        verify {
            secondListenerMock.invoke(State(State.Name.IDLE))
        }

        verify(exactly = 1) {
            listenerMock.invoke(State(State.Name.IDLE))
        }
    }
}

class TestValue

class TestError

class TestErrorFactory(private val error: TestError? = null) : ErrorFactory<TestError> {
    override fun create(throwable: Throwable) = error!!
}
