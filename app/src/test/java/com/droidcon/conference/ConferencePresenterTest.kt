package com.droidcon.conference

import com.droidcon.dispatcher.FakeDispatcher
import com.droidcon.state.State
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class ConferencePresenterTest {

    @Test
    fun `Given a loaded conference When state is updated Then show conference name`() {
        val view = mockk<ConferenceView>(relaxed = true)
        val presenter = ConferencePresenter(FakeDispatcher(), view)

        presenter.invoke(State(State.Name.LOADED, Conference("1", "Droidcon")))

        verify {
            view.hideError()
            view.hideLoading()
            view.showConferenceName("Droidcon")
        }
    }
}
