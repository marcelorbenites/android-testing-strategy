package com.droidcon.state

abstract class StateMachine<T, E>(
    private val errorFactory: ErrorFactory<E>,
    private val dispatcher: Dispatcher,
    private var currentState: State<T, E> = State(State.Name.IDLE),
    private val listeners: MutableList<(State<T, E>) -> Unit> = mutableListOf()
) {

    abstract fun start()

    fun addStateChangedListener(listener: (State<T, E>) -> Unit) {
        this.listeners.add(listener)
        listener.invoke(currentState)
    }

    fun move(function: (State<T, E>) -> T) {
        dispatcher.dispatch({
            updateState(State(State.Name.LOADING, currentState.value, null))
            updateState(State(State.Name.LOADED, function.invoke(currentState), null))
        }, {
            updateState(State(State.Name.ERROR, currentState.value, errorFactory.create(it)))
        })
    }

    private fun updateState(state: State<T, E>) {
        currentState = state
        for (listener in listeners) {
            listener.invoke(currentState)
        }
    }
}
