package com.ekalips.base.vm

import android.arch.lifecycle.ViewModel
import com.ekalips.base.state.BaseViewState
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Ekalips on 2/7/18.
 */

abstract class BaseViewModel<out State : BaseViewState> : ViewModel() {

    abstract val state: State
    protected val disposer = CompositeDisposable()

    open fun onActive() {

    }

    open fun onInactive() {

    }

    override fun onCleared() {
        disposer.dispose()
    }

    protected fun goBack() {
        state.back.call()
    }
}