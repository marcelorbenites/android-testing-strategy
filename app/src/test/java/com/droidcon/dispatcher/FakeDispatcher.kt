package com.droidcon.dispatcher

import com.droidcon.state.Dispatcher

class FakeDispatcher: Dispatcher {
    override fun dispatch(execute: () -> Unit, error: (Throwable) -> Unit) {
        try {
            execute.invoke()
        } catch (throwable: Throwable) {
            error.invoke(throwable)
        }
    }

    override fun dispatch(execute: () -> Unit) {
        execute.invoke()
    }
}
