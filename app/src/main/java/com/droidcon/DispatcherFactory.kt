package com.droidcon

import com.droidcon.state.Dispatcher

interface DispatcherFactory {
    fun createSerialDispatcher(name: String): Dispatcher
    fun createMainDispatcher(): Dispatcher
}
