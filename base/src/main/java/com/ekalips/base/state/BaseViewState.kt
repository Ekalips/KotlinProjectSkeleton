package com.ekalips.base.state

import android.arch.lifecycle.MediatorLiveData
import com.ekalips.base.vm.SingleLiveEvent

/**
 * Created by Ekalips on 2/7/18.
 */

open class BaseViewState {

    val loading = MediatorLiveData<Boolean>()
    val toast = SingleLiveEvent<String>()
    val back = SingleLiveEvent<Void>()

    init {
        loading.value = false
    }

}