package com.droidcon.state

abstract class StateMachine<T, E>(
    private val errorFactory: ErrorFactory<E>,
    protected var currentState: State<T, E> = State(State.Name.IDLE),
    private val listeners: MutableList<(State<T, E>) -> Unit> = mutableListOf()
) {

    abstract fun start()

    fun addStateChangedListener(listener: (State<T, E>) -> Unit) {
        this.listeners.add(listener)
        listener.invoke(currentState)
    }

    fun removeStateChangedListener(listener: (State<T, E>) -> Unit) {
        this.listeners.remove(listener)
    }

    fun moveToLoading() {
        updateState(State(State.Name.LOADING, currentState.value, null))
    }

    fun moveToLoaded(value: T) {
        updateState(State(State.Name.LOADED, value, null))
    }

    fun moveToError(throwable: Throwable) {
        updateState(State(State.Name.ERROR, currentState.value, errorFactory.create(throwable)))
    }

    private fun updateState(state: State<T, E>) {
        currentState = state
        for (listener in listeners) {
            listener.invoke(currentState)
        }
    }
}
