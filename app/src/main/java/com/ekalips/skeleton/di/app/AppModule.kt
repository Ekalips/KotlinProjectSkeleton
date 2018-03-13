package com.ekalips.skeleton.di.app

import android.app.Application
import android.content.Context
import com.ekalips.skeleton.di.modules.BoxModule
import com.ekalips.skeleton.di.modules.NetworkModule
import com.ekalips.skeleton.di.modules.ViewModelModule
import dagger.Binds
import dagger.Module

/**
 * Created by Ekalips on 10/2/17.
 */

@Module(includes = [ViewModelModule::class, BoxModule::class, NetworkModule::class])
abstract class AppModule {

    @Binds
    abstract fun bindContext(application: Application): Context
}
