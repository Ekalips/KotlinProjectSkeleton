package com.ekalips.skeleton.stuff.base

import android.util.Log
import com.ekalips.skeleton.stuff.InsignificantError
import com.ekalips.skeleton.stuff.navigation.Navigate
import com.ekalips.skeleton.stuff.navigation.Place
import com.ekalips.base.state.BaseViewState
import com.ekalips.base.vm.BaseViewModel
import com.ekalips.base.vm.SingleLiveEvent


abstract class GHViewModel<out ViewState : BaseViewState> : BaseViewModel<ViewState>() {

    val navigationTrigger = SingleLiveEvent<Navigate>()
    protected val lock = Any()

    fun navigate(place: Place, payload: Any? = null) {
        navigationTrigger.postValue(Navigate(place, payload))
    }

    protected fun handleError(throwable: Throwable?) {
        if (throwable is InsignificantError) {
            Log.w(javaClass.simpleName, "handledCommonError: ${throwable.cause}")
        } else if (throwable != null) handleUncommonError(throwable)
        else Log.w(javaClass.simpleName, "handledCommonError: received null throwable")
    }

    open fun handleUncommonError(throwable: Throwable?) {
        throwable?.let {
            Log.e(javaClass.simpleName, "Unhandled error: $throwable")
            throw throwable
        }
    }

}