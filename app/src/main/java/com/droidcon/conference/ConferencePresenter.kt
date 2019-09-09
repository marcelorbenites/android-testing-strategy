package com.droidcon.conference

import com.droidcon.gateway.GatewayError
import com.droidcon.state.Dispatcher
import com.droidcon.state.Presenter
import com.droidcon.state.State

class ConferencePresenter(
    mainDispatcher: Dispatcher,
    viewModel: ConferenceViewModel
) : Presenter<ConferenceViewModel, Conference, GatewayError>(viewModel, mainDispatcher) {

    override fun onStateUpdated(
        state: State<Conference, GatewayError>,
        viewModel: ConferenceViewModel
    ) {
        when (state.name) {
            State.Name.IDLE -> {
                viewModel.loading = false
                viewModel.networkError = false
                viewModel.unknownError = false
            }
            State.Name.LOADING -> {
                viewModel.loading = true
                viewModel.networkError = false
                viewModel.unknownError = false
            }
            State.Name.LOADED -> {
                val conference = state.value!!
                viewModel.loading = false
                viewModel.networkError = false
                viewModel.unknownError = false
                viewModel.name = conference.name
            }
            State.Name.ERROR -> {
                val error = state.error!!
                viewModel.loading = false
                viewModel.networkError = error.network
                viewModel.unknownError = !error.network
            }
        }
    }
}
