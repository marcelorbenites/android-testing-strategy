package com.droidcon.conference

import com.droidcon.gateway.GatewayError
import com.droidcon.state.Dispatcher
import com.droidcon.state.State

class ConferencePresenter(
    private val mainDispatcher: Dispatcher,
    private val view: ConferenceView
) : (State<Conference, GatewayError>) -> Unit {

    override fun invoke(state: State<Conference, GatewayError>) {
        mainDispatcher.dispatch {
            when (state.name) {
                State.Name.IDLE -> {
                    view.hideLoading()
                    view.hideError()
                    view.hideConferenceName()
                }
                State.Name.LOADING -> {
                    view.showLoading()
                    view.hideError()
                    view.hideConferenceName()
                }
                State.Name.LOADED -> {
                    val conference = state.value!!
                    view.hideLoading()
                    view.hideError()
                    view.showConferenceName(conference.name)
                }
                State.Name.ERROR -> {
                    view.showError()
                    view.hideConferenceName()
                    view.hideLoading()
                }
            }
        }
    }
}
