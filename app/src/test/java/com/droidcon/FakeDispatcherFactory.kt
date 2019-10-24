package com.droidcon

import com.droidcon.dispatcher.FakeDispatcher

class FakeDispatcherFactory : DispatcherFactory {
    override fun createSerialDispatcher(name: String) = FakeDispatcher()
    override fun createMainDispatcher() = FakeDispatcher()
}
