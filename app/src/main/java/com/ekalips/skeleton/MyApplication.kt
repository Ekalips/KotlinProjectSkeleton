package com.ekalips.skeleton

import android.app.Activity
import android.app.Application
import android.app.Service
import com.ekalips.skeleton.di.app.DaggerAppComponent
import com.ekalips.skeleton.stuff.ErrorHandler
import com.ekalips.skeleton.stuff.InsignificantError
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import javax.inject.Inject

class MyApplication : Application(), HasActivityInjector, HasServiceInjector {


    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var serviceInjector: DispatchingAndroidInjector<Service>

    @Inject
    lateinit var errorHandler: ErrorHandler

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)

        setUpErrorHandlers()
    }

    private fun setUpErrorHandlers() {
        val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, exception -> if (exception !is InsignificantError) defaultHandler.uncaughtException(thread, exception) }

        RxJavaPlugins.setErrorHandler {
            var exception = it
            if (exception is UndeliverableException) {
                exception = exception.cause
            }
            if (!errorHandler.handleError(exception)) {
                defaultHandler.uncaughtException(Thread.currentThread(), exception)
            }
        }
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
    override fun serviceInjector(): AndroidInjector<Service> = serviceInjector
}