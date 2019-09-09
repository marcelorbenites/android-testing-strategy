package com.droidcon.state

abstract class Presenter<V : ViewModel, T, E>(
    private val viewModel: V,
    private val dispatcher: Dispatcher
) : (State<T, E>) -> Unit {

    override fun invoke(state: State<T, E>) {
        dispatcher.dispatch {
            onStateUpdated(state, viewModel)
            viewModel.notifyObserver()
        }
    }

    abstract fun onStateUpdated(state: State<T, E>, viewModel: V)
}
