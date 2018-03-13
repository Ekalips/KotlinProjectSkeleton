package com.ekalips.skeleton.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ekalips.skeleton.welcome.mvvm.vm.SplashScreenViewModel
import com.ekalips.base.di.ViewModelKey
import com.ekalips.base.vm.BaseViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


/**
 * Created by Ekalips on 2/7/18.
 */
@Module
abstract class ViewModelModule {

    @Binds
    @ViewModelKey(value = SplashScreenViewModel::class)
    @IntoMap
    internal abstract fun bindSplasVM(splashScreenViewModel: SplashScreenViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: BaseViewModelFactory): ViewModelProvider.Factory
}