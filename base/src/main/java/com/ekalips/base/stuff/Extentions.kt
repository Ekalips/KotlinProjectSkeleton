package com.ekalips.base.stuff

import android.content.Context

inline fun Boolean?.ifThenElse(action: () -> Any): Any? {
    return if (this == true) {
        action()
        Any()
    } else null
}

inline fun Boolean?.ifThen(action: () -> Any) {
    if (this == true) {
        action()
    }
}

fun Context?.getStatusBarHeight(): Int {
    val res = this?.resources
    val resourceId = res?.getIdentifier("status_bar_height", "dimen", "android")
    return if (res != null && resourceId != null && resourceId > 0) {
        res.getDimensionPixelSize(resourceId)
    } else 0
}

fun Context?.getBottomNavBarHeight(): Int {
    val res = this?.resources
    val resourceId = res?.getIdentifier("navigation_bar_height", "dimen", "android")
    return if (res != null && resourceId != null && resourceId > 0) {
        res.getDimensionPixelSize(resourceId)
    } else 0
}