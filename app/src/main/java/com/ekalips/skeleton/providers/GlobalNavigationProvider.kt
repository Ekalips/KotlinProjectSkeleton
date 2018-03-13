package com.ekalips.skeleton.providers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View
import com.ekalips.skeleton.welcome.mvvm.view.SplashActivity
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Ekalips on 2/7/18.
 */


@Singleton
class GlobalNavigationProvider @Inject constructor() {
    infix fun navigateToSplashScreen(context: Context) {
        context.startActivity(Intent(context, SplashActivity::class.java))
    }
}