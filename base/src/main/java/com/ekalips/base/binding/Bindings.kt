package com.ekalips.base.binding

import android.databinding.BindingAdapter
import android.view.View

@BindingAdapter("android:visibility")
fun setVisibility(view: View, visible: Boolean?) {
    view.visibility = if (visible == true) View.VISIBLE else View.GONE
}