package com.droidcon.conference

import com.droidcon.dispatcher.FakeDispatcher
import com.droidcon.state.State
import org.junit.Assert.assertEquals
import org.junit.Test

class ConferencePresenterTest {

    @Test
    fun `Given a loaded conference When state is updated Then show conference name`() {
        val viewModel = ConferenceViewModel()
        val presenter = ConferencePresenter(FakeDispatcher(), viewModel)

        presenter.invoke(State(State.Name.LOADED, Conference("1", "Droidcon")))

        assertEquals(false, viewModel.showError)
        assertEquals(false, viewModel.showLoading)
        assertEquals(false, viewModel.hideName)
        assertEquals("Droidcon", viewModel.name)
    }
}
