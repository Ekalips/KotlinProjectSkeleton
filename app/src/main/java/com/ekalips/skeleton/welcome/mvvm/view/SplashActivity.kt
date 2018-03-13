package com.ekalips.skeleton.welcome.mvvm.view

import android.databinding.ViewDataBinding
import com.ekalips.skeleton.stuff.base.GHActivity
import com.ekalips.skeleton.welcome.mvvm.vm.SplashScreenViewModel

class SplashActivity : GHActivity<SplashScreenViewModel, ViewDataBinding>() {
    override val vmClass: Class<SplashScreenViewModel> = SplashScreenViewModel::class.java
    override val layoutId: Int = 0
    override val brRes: Int = 0
    override val inflate: Boolean = false
}
