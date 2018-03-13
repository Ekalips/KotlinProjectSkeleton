package com.ekalips.base.rv

import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.support.v7.util.ListUpdateCallback
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

abstract class BindingRecyclerViewAdapter<ViewBinding : ViewDataBinding, DataType> : RecyclerView.Adapter<BindingViewHolder<ViewBinding>>() {

    abstract val resId: Int
    abstract val diffComparator: DiffCallback.DiffComparator<DataType>?
    val data: MutableList<DataType> = ArrayList()
    override fun getItemCount(): Int = data.size

    private val dataLock = Any()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ViewBinding> = BindingViewHolder(resId, parent)

    fun setData(newData: List<DataType>?) {
        synchronized(dataLock, {
            val result = DiffUtil.calculateDiff(DiffCallback(data, newData
                    ?: ArrayList(), diffComparator))
            data.clear()
            newData?.let { data.addAll(it) }
            result.dispatchUpdatesTo(dataUpdateCallback)
        })
    }

    private val dataUpdateCallback: ListUpdateCallback = object : ListUpdateCallback {
        override fun onChanged(position: Int, count: Int, payload: Any?) {
            if (count == 1) {
                notifyItemChanged(position, payload)
            } else {
                notifyItemRangeChanged(position, count, payload)
            }
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            notifyItemMoved(fromPosition, toPosition)
        }

        override fun onInserted(position: Int, count: Int) {
            if (count == 1) {
                notifyItemInserted(position)
            } else {
                notifyItemRangeInserted(position, count)
            }
        }

        override fun onRemoved(position: Int, count: Int) {
            if (count == 1) {
                notifyItemRemoved(position)
            } else {
                notifyItemRangeRemoved(position, count)
            }
        }
    }
}