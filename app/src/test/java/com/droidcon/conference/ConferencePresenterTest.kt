package com.droidcon.conference

import com.droidcon.dispatcher.FakeDispatcher
import com.droidcon.state.State
import org.junit.Assert.assertEquals
import org.junit.Test

class ConferencePresenterTest {

    @Test
    fun `Given a loaded conference When state is updated Then view model should have conference name`() {
        val viewModel = ConferenceViewModel()
        val presenter = ConferencePresenter(FakeDispatcher(), viewModel)

        presenter.invoke(State(State.Name.LOADED, Conference("1", "Droidcon")))

        assertEquals("Droidcon", viewModel.name)
        assertEquals(false, viewModel.loading)
        assertEquals(false, viewModel.networkError)
        assertEquals(false, viewModel.unknownError)
    }
}
