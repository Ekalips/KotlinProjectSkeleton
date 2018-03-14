package com.ekalips.skeleton.welcome.mvvm.vm

import com.ekalips.skeleton.stuff.base.SkeletonViewModel
import com.ekalips.base.state.BaseViewState
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor() : SkeletonViewModel<BaseViewState>() {
    override val state: BaseViewState = BaseViewState()
}