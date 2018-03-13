package com.ekalips.base.rv

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class BindingViewHolder<out ViewBinding : ViewDataBinding> : RecyclerView.ViewHolder {

    val binding: ViewBinding

    constructor(resId: Int, parent: ViewGroup) : this(DataBindingUtil.inflate(LayoutInflater.from(parent.context), resId, parent, false))

    constructor(viewBinding: ViewBinding) : super(viewBinding.root) {
        this.binding = viewBinding
    }

}