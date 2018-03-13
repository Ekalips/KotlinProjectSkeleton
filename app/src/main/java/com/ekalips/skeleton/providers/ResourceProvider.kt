package com.ekalips.skeleton.providers

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.support.v7.content.res.AppCompatResources
import android.util.TypedValue

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ekalips on 11/20/17.
 */

@Singleton
class ResourceProvider @Inject
constructor(private val context: Context) {

    fun getString(@StringRes res: Int, vararg args: Any): String {
        return context.getString(res, *args)
    }

    @ColorInt
    fun getColor(@ColorRes color: Int): Int {
        return ContextCompat.getColor(context, color)
    }

    fun getDrawable(@DrawableRes icon: Int): Drawable? {
        return AppCompatResources.getDrawable(context, icon)
    }

    fun getSP(value: Float): Float = getTypedValue(value, TypedValue.COMPLEX_UNIT_SP)

    fun getDP(value: Float): Float = getTypedValue(value, TypedValue.COMPLEX_UNIT_DIP)

    fun getTypedValue(value: Float, unit: Int) = TypedValue.applyDimension(unit, value, context.resources.displayMetrics)

}
