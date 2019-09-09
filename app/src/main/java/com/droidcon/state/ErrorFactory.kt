package com.droidcon.state

interface ErrorFactory<E> {
    fun create(throwable: Throwable): E
}
