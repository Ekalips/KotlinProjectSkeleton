package com.ekalips.base.stuff.views

import android.graphics.Outline
import android.util.TypedValue
import android.view.View
import android.view.ViewOutlineProvider

class RoundedCornersOutlineProvider constructor(private val cornerRadius: Float) : ViewOutlineProvider() {

    override fun getOutline(view: View?, outline: Outline?) {
        if (view != null && outline != null) {
            val pxRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, cornerRadius, view.context.resources.displayMetrics)
            outline.setRoundRect(0, 0, view.width, view.height, pxRadius)
        }
    }

}