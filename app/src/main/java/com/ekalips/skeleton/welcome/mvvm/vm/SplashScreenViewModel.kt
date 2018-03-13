package com.ekalips.skeleton.welcome.mvvm.vm

import com.ekalips.skeleton.stuff.base.GHViewModel
import com.ekalips.base.state.BaseViewState
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor() : GHViewModel<BaseViewState>() {
    override val state: BaseViewState = BaseViewState()
}