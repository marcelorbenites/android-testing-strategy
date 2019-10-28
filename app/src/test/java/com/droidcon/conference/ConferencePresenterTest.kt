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
        assertEquals(false, viewModel.showRetry)
        assertEquals("Droidcon", viewModel.name)
    }

    @Test
    fun `Given a loading state When state is updated Then show loading`() {
        val viewModel = ConferenceViewModel()
        val presenter = ConferencePresenter(FakeDispatcher(), viewModel)

        presenter.invoke(State(State.Name.LOADING))

        assertEquals(false, viewModel.showError)
        assertEquals(true, viewModel.showLoading)
        assertEquals(true, viewModel.hideName)
        assertEquals(false, viewModel.showRetry)
        assertEquals("", viewModel.name)
    }

    @Test
    fun `Given a error state When state is updated Then show error And show retry`() {
        val viewModel = ConferenceViewModel()
        val presenter = ConferencePresenter(FakeDispatcher(), viewModel)

        presenter.invoke(State(State.Name.ERROR))

        assertEquals(true, viewModel.showError)
        assertEquals(false, viewModel.showLoading)
        assertEquals(true, viewModel.hideName)
        assertEquals(true, viewModel.showRetry)
        assertEquals("", viewModel.name)
    }
}
