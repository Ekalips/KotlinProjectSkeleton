package com.ekalips.base.rv

import android.databinding.ViewDataBinding
import android.support.v7.recyclerview.extensions.AsyncDifferConfig
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.util.ListUpdateCallback
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

abstract class BindingRecyclerViewAdapter<ViewBinding : ViewDataBinding, DataType> : ListAdapter<DataType, BindingViewHolder<ViewBinding>> {

    constructor() : super(DefaultDiffItemCallback<DataType>())
    constructor(comparator: DiffUtil.ItemCallback<DataType>) : super(comparator)
    constructor(asyncDifferConfig: AsyncDifferConfig<DataType>) : super(asyncDifferConfig)

    abstract val resId: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ViewBinding> = BindingViewHolder(resId, parent)
}

class DefaultDiffItemCallback<DataType> : DiffUtil.ItemCallback<DataType>() {
    override fun areItemsTheSame(oldItem: DataType, newItem: DataType): Boolean = false
    override fun areContentsTheSame(oldItem: DataType, newItem: DataType): Boolean = false
}