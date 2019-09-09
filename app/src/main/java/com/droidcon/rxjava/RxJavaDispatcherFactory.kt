package com.droidcon.rxjava

import android.os.HandlerThread
import com.droidcon.DispatcherFactory
import com.droidcon.state.Dispatcher
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class RxJavaDispatcherFactory(
    private val publishScheduler: Scheduler
) : DispatcherFactory {
    override fun createSerialDispatcher(name: String): Dispatcher {
        val thread = HandlerThread(name)
        thread.start()
        return RxJavaDispatcher(AndroidSchedulers.from(thread.looper))
    }

    override fun createMainDispatcher(): Dispatcher = RxJavaDispatcher(
        publishScheduler
    )
}
