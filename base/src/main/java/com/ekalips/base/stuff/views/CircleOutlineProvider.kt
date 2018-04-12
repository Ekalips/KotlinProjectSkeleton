package com.ekalips.base.stuff.views

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider

class CircleOutlineProvider : ViewOutlineProvider() {
    override fun getOutline(view: View?, outline: Outline?) {
        if (outline != null && view != null) {
            val width = view.width
            val height = view.height
            outline.setOval(0, 0, width, height)
        }
    }

}