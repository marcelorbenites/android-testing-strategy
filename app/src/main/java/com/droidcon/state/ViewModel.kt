package com.droidcon.state

abstract class ViewModel {

    private var observer: (() -> Unit)? = null

    fun notifyObserver() {
        observer?.invoke()
    }

    fun setObserver(observer: () -> Unit) {
        this.observer = observer
        notifyObserver()
    }

    fun removeObserver() {
        this.observer = null
    }
}
