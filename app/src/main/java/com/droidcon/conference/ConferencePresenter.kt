package com.droidcon.conference

import com.droidcon.gateway.GatewayError
import com.droidcon.state.Dispatcher
import com.droidcon.state.State

class ConferencePresenter(
    private val mainDispatcher: Dispatcher,
    private val viewModel: ConferenceViewModel
) : (State<Conference, GatewayError>) -> Unit {

    override fun invoke(state: State<Conference, GatewayError>) {
        mainDispatcher.dispatch {
            when (state.name) {
                State.Name.IDLE -> {
                    viewModel.showLoading = false
                    viewModel.showError = false
                    viewModel.hideName = true
                }
                State.Name.LOADING -> {
                    viewModel.showLoading = true
                    viewModel.showError = false
                    viewModel.hideName = true
                }
                State.Name.LOADED -> {
                    val conference = state.value!!
                    viewModel.showLoading = false
                    viewModel.showError = false
                    viewModel.hideName = false
                    viewModel.name = conference.name
                }
                State.Name.ERROR -> {
                    viewModel.showLoading = false
                    viewModel.showError = true
                    viewModel.hideName = true
                }
            }
            viewModel.notifyObserver()
        }
    }
}
