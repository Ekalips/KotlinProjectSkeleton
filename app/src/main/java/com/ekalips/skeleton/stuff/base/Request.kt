package com.ekalips.skeleton.stuff.base

import android.arch.lifecycle.LiveData
import io.reactivex.disposables.CompositeDisposable

class RequestLiveData<T> : LiveData<Request<T>>() {

    private val mutex = Any()
    val disposer = CompositeDisposable()

    init {
        value = Request(null, null, null)
    }

    fun result(result: T?) {
        synchronized(mutex, {
            postValue(value.also { it?.result = result })
        })
    }

    fun loading(loading: Boolean?) {
        synchronized(mutex, {
            postValue(value.also { it?.loading = loading })
        })
    }

    fun error(error: Throwable?) {
        synchronized(mutex, {
            postValue(value.also { it?.error = error })
        })
    }
}

data class Request<T>(var result: T?,
                      var loading: Boolean?,
                      var error: Throwable?)