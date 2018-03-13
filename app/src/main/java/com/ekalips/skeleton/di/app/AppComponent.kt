package com.ekalips.skeleton.di.app

import android.app.Application
import com.ekalips.skeleton.MyApplication
import com.ekalips.skeleton.di.modules.ActivityBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

/**
 * Created by Ekalips on 10/2/17.
 */

@Singleton
@Component(modules = [AndroidInjectionModule::class, AndroidSupportInjectionModule::class, AppModule::class, ActivityBuilderModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: MyApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
