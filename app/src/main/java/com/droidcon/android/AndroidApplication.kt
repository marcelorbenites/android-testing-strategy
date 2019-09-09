package com.droidcon.android

import android.app.Application
import com.droidcon.DependencyManager
import com.droidcon.DroidconApplication
import com.droidcon.conference.OkHttpConferenceGateway
import com.droidcon.rxjava.RxJavaDispatcherFactory
import io.reactivex.android.schedulers.AndroidSchedulers

class AndroidApplication : Application() {

    lateinit var dependencyManager: DependencyManager

    override fun onCreate() {
        super.onCreate()
        dependencyManager = DroidconApplication.Builder()
            .registerDispatcherFactory(lazy { RxJavaDispatcherFactory(AndroidSchedulers.mainThread()) })
            .registerConferenceGateway(lazy { OkHttpConferenceGateway() })
            .start()
    }
}
