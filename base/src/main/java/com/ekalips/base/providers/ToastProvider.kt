package com.ekalips.base.providers

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.support.annotation.MainThread
import android.support.annotation.StringRes
import android.widget.Toast
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ekalips on 2/7/18.
 */
@SuppressLint("CheckResult")
@Singleton
class ToastProvider @Inject constructor(private val context: Context) {

    private val handlerThread = HandlerThread("HandlerThread")
    private val mainThreadHandler: Handler

    init {
        handlerThread.start()
        mainThreadHandler = Handler(handlerThread.looper)
    }

    fun showToast(text: String) {
        showToast(text, Toast.LENGTH_SHORT)
    }

    fun showToast(@StringRes textRes: Int) {
        showToast(context.getString(textRes))
    }

    fun showToast(@StringRes textRes: Int, duration: Int) {
        showToast(context.getString(textRes), duration)
    }

    @SuppressLint("ShowToast")
    fun showToast(text: String, duration: Int) {
        checkThreadAndShow(text, duration)
    }

    private fun checkThreadAndShow(text: String, duration: Int) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            showToastInternal(text, duration)
        } else {
            mainThreadHandler.post { showToastInternal(text, duration) }
        }
    }

    @MainThread
    private fun showToastInternal(text: String, duration: Int) {
        Toast.makeText(context, text, duration).show()
    }
}